/*
 * Copyright (c) 2021-2021, jad (cxxwl96@sina.com).
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

package com.jad.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * UrlUtil
 *
 * @author cxxwl96
 * @since 2021/6/20 20:38
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jad.security")
public class UrlConfig {

    // 首页
    public static final String HOME_URL = "/";

    // 错误页面
    public static final String ERROR_URL = "/error";

    // 登录URL
    public static final String LOGIN_URL = "/auth/login";

    // 登出URL
    public static final String LOGOUT_URL = "/auth/logout";

    // 注册URL
    public static final String REGISTER = "/auth/register";

    // 白名单列表
    private static final Set<String> PERMIT_URLS = CollUtil.newHashSet(HOME_URL, ERROR_URL, LOGIN_URL, LOGOUT_URL,
        REGISTER);

    // 配置文件配置的白名单
    private String[] urlWhiteList;

    /**
     * 获取所有URL白名单
     * - 包括配置文件配置的白名单
     *
     * @return URL白名单
     */
    public String[] getAllPermitUrls() {
        // 添加配置文件配置的白名单
        if (urlWhiteList != null) {
            for (String urlsStr : urlWhiteList) {
                String[] urls = urlsStr.split(",");
                for (String url : urls) {
                    if (StrUtil.isNotBlank(url)) {
                        PERMIT_URLS.add(url.trim());
                    }
                }
            }
        }
        return PERMIT_URLS.toArray(new String[0]);
    }

    /**
     * 添加白名单
     *
     * @param permitUrls 白名单URL
     */
    public static void addPermitUrls(Collection<String> permitUrls) {
        if (CollUtil.isNotEmpty(permitUrls)) {
            PERMIT_URLS.addAll(permitUrls);
        }
    }

    /**
     * 添加白名单
     *
     * @param permitUrls 白名单URL
     */
    public static void addPermitUrls(String... permitUrls) {
        if (permitUrls != null) {
            PERMIT_URLS.addAll(Arrays.asList(permitUrls));
        }
    }

    /**
     * 添加白名单
     *
     * @param permitUrl 白名单URL
     */
    public static void addPermitUrl(String permitUrl) {
        if (StrUtil.isNotBlank(permitUrl)) {
            PERMIT_URLS.add(permitUrl);
        }
    }
}
