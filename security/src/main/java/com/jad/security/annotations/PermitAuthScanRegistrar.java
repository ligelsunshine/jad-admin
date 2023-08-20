/*
 * Copyright (c) 2021-2023, cxxwl96.com (cxxwl96@sina.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jad.security.annotations;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jad.common.config.UrlConfig;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证注解扫描器
 *
 * @author cxxwl96
 * @since 2023/8/20 17:41
 */
@Slf4j
public class PermitAuthScanRegistrar implements ImportBeanDefinitionRegistrar {
    private final Class<?>[] mappings = {
        RequestMapping.class, PostMapping.class, DeleteMapping.class, PutMapping.class, GetMapping.class,
        PatchMapping.class,
    };

    @SneakyThrows
    @Override
    public void registerBeanDefinitions(@Nullable AnnotationMetadata metadata,
        @Nullable BeanDefinitionRegistry registry) {

        Map<String, Object> defaultAttrs = metadata.getAnnotationAttributes(PermitAuthScan.class.getName(), true);
        String[] basePackages = (String[]) defaultAttrs.get("basePackages");
        String packages = ArrayUtil.isEmpty(basePackages) ? "null" : String.join(", ", basePackages);
        // 打印basePackages
        log.info("PermitAuth package scan: " + packages);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        Set<String> permitUrls = new HashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                Class<?> beanClass = Class.forName(candidate.getBeanClassName());
                permitUrls.addAll(scanPermitUrl(beanClass)); // 扫描白名单url
            }
        }
        log.info("PermitUrls: {}", permitUrls);
        // 添加白名单
        UrlConfig.addPermitUrls(permitUrls);
    }

    private Set<String> scanPermitUrl(Class<?> beanClass) {
        Set<String> permitUrls = new HashSet<>();
        // 获取类mapping的值
        String[] classMappings = getMappingValue(beanClass);
        // 类上是否有Authenticate
        if (beanClass.isAnnotationPresent(PermitAuth.class)) {
            PermitAuth permitAuth = beanClass.getAnnotation(PermitAuth.class);
            if (permitAuth.permit()) {
                // 类上是否有RequestMapping
                if (classMappings != null) {
                    // 添加白名单
                    for (String classMapping : classMappings) {
                        permitUrls.add(classMapping + "/**");
                    }
                }
            }
        }
        // 获取类的方法
        Method[] methods = beanClass.getDeclaredMethods();
        for (Method method : methods) {
            // 获取方法mapping的值
            String[] methodMappings = getMappingValue(method);
            // 方法上是否有Authenticate
            if (method.isAnnotationPresent(PermitAuth.class)) {
                PermitAuth permitAuth = method.getAnnotation(PermitAuth.class);
                if (permitAuth.permit()) {
                    // 方法上是否有RequestMapping
                    if (methodMappings != null) {
                        if (classMappings == null) { // 类上没有RequestMapping
                            permitUrls.addAll(Arrays.asList(methodMappings));
                        } else { // 类上有RequestMapping
                            for (String classMapping : classMappings) {
                                for (String methodMapping : methodMappings) {
                                    permitUrls.add(classMapping + methodMapping);
                                }
                            }
                        }

                    }
                }
            }
        }
        return permitUrls;
    }

    private String[] getMappingValue(AnnotatedElement element) {
        for (Annotation annotation : element.getAnnotations()) {
            for (Class<?> mapping : mappings) {
                if (annotation.annotationType().equals(mapping)) {
                    // 获取value值
                    Object obj = ReflectUtil.invoke(annotation, "value");
                    JSONArray array = JSON.parseArray(JSON.toJSONString(obj));
                    String[] value = new String[array.size()];
                    for (int i = 0; i < array.size(); i++) {
                        value[0] = array.getString(i);
                    }
                    return value;
                }
            }
        }
        return null;
    }
}
