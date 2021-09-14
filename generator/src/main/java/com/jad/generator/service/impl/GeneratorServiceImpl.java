/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.service.impl;

import com.jad.common.utils.SystemUtil;
import com.jad.generator.common.FreemarkerGenerator;
import com.jad.generator.config.PathConfig;
import com.jad.generator.enums.FieldType;
import com.jad.generator.model.FieldSchema;
import com.jad.generator.model.Model;
import com.jad.generator.service.GeneratorService;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

/**
 * GeneratorServiceImpl
 *
 * @author cxxwl96
 * @since 2021/9/11 23:16
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    /**
     * 生成后端代码
     *
     * @param model model
     */
    @Override
    public void generateBack(Model model) {
        final PathConfig pathConfig = new PathConfig(model.getModule());
        if (model.getGenerateConfig().isEntity()) {
            // 生成entity
            generateEntity(model, pathConfig);
        }
        if (model.getGenerateConfig().isService()) {
            // 生成service
            generateService(model, pathConfig);
            // 生成serviceImpl
            generateServiceImpl(model, pathConfig);
        }
    }

    /**
     * 生成前端代码
     *
     * @param model model
     */
    @Override
    public void generateFront(Model model) {

    }

    /**
     * 生成entity
     *
     * @param model model
     * @param pathConfig pathConfig
     */
    private void generateEntity(Model model, PathConfig pathConfig) {
        final List<String> imports = getEntityImport(model, pathConfig);
        final Map<String, Object> paramMap = getParamMap(model);
        paramMap.put("package", pathConfig.getEntityPackage());
        paramMap.put("imports", imports);
        new FreemarkerGenerator("templates/back/entity.java.ftl",
            pathConfig.getEntityPath() + "/" + model.getBigHump() + ".java").process(paramMap);
        generateEnum(model, pathConfig);
    }

    /**
     * 生成enum
     *
     * @param model model
     * @param pathConfig pathConfig
     */
    private void generateEnum(Model model, PathConfig pathConfig) {
        final List<FieldSchema> fieldSchemas = model.getFieldSchema();
        if (CollUtil.isEmpty(fieldSchemas)) {
            return;
        }
        final Map<String, Object> paramMap = getParamMap(model);
        paramMap.put("package", pathConfig.getEnumPackage());
        fieldSchemas.forEach(fieldSchema -> {
            if (fieldSchema.getType() == FieldType.ENUM) {
                paramMap.put("enumParam", fieldSchema);
                new FreemarkerGenerator("templates/back/enum.java.ftl",
                    pathConfig.getEnumPath() + "/" + fieldSchema.getBigHump() + ".java").process(paramMap);
            }
        });
    }

    /**
     * 生成生成service
     *
     * @param model model
     * @param pathConfig pathConfig
     */
    private void generateService(Model model, PathConfig pathConfig) {
        final Map<String, Object> paramMap = getParamMap(model);
        paramMap.put("package", pathConfig.getServicePackage());
        new FreemarkerGenerator("templates/back/service.java.ftl",
            pathConfig.getServicePath() + "/" + model.getBigHump() + "Service.java").process(paramMap);
        generateEnum(model, pathConfig);
    }

    /**
     * 生成serviceImpl
     *
     * @param model model
     * @param pathConfig pathConfig
     */
    private void generateServiceImpl(Model model, PathConfig pathConfig) {
        final Map<String, Object> paramMap = getParamMap(model);
        paramMap.put("package", pathConfig.getServiceImplPackage());

    }

    /**
     * 获取import列表
     *
     * @param model model
     * @param pathConfig pathConfig
     * @return import列表
     */
    private List<String> getEntityImport(Model model, PathConfig pathConfig) {
        final List<FieldSchema> fieldSchemas = model.getFieldSchema();
        final Set<String> setImports = new HashSet<>();
        setImports.add("com.baomidou.mybatisplus.annotation.TableName");
        setImports.add("java.io.Serializable");
        setImports.add("io.swagger.annotations.ApiModel");
        setImports.add("io.swagger.annotations.ApiModelProperty");
        setImports.add("lombok.Data");
        setImports.add("lombok.EqualsAndHashCode");
        setImports.add("lombok.experimental.Accessors");
        if (model.isLogic()) {
            setImports.add("com.baomidou.mybatisplus.annotation.FieldStrategy");
            setImports.add("com.baomidou.mybatisplus.annotation.FieldFill");
            setImports.add("com.baomidou.mybatisplus.annotation.TableField");
            setImports.add("com.baomidou.mybatisplus.annotation.TableLogic");
        }
        if (model.isTreeModel()) {
            setImports.add("com.jad.common.base.entity.TreeNode");
        } else {
            setImports.add("com.jad.common.base.entity.BaseEntity");
        }
        if (CollUtil.isNotEmpty(fieldSchemas)) {
            fieldSchemas.forEach(fieldSchema -> {
                if (fieldSchema.isRequire()) {
                    if (fieldSchema.getType() == FieldType.STRING) {
                        setImports.add("javax.validation.constraints.NotBlank");
                    } else {
                        setImports.add("javax.validation.constraints.NotNull");
                    }
                }
                if (fieldSchema.getType() == FieldType.DATE) {
                    setImports.add("java.time.LocalDateTime");
                    final String defaultVal = (String) fieldSchema.getDefaultVal();
                    if (fieldSchema.isRequire() && StrUtil.isNotBlank(defaultVal) && defaultVal.matches(
                        "^\\d{4}(-\\d{2}){2}T\\d{2}(:\\d{2}){2}$")) {
                        setImports.add("cn.hutool.core.date.LocalDateTimeUtil");
                    }
                }
                // 添加自定义枚举
                if (fieldSchema.getType() == FieldType.ENUM) {
                    setImports.add(pathConfig.getEnumPackage() + "." + fieldSchema.getBigHump());
                }
                // 添加正则
                if (fieldSchema.getRules() != null && fieldSchema.getRules().size() > 0) {
                    setImports.add("javax.validation.constraints.Pattern");
                }
            });
        }
        ArrayList<String> imports = new ArrayList<>(setImports);
        return imports.stream().sorted().collect(Collectors.toList());
    }

    private Map<String, Object> getParamMap(Model model) {
        final Map<String, Object> paramMap = new HashMap<>();
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        final String dateTimeStr = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(date);
        final String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        final String timeStr = new SimpleDateFormat("HH:mm:ss").format(date);
        paramMap.put("author", SystemUtil.getUsername());
        paramMap.put("model", model);
        paramMap.put("dateTime", dateTimeStr);
        paramMap.put("date", dateStr);
        paramMap.put("time", timeStr);
        paramMap.put("year", String.valueOf(calendar.get(Calendar.YEAR)));
        paramMap.put("month", calendar.get(Calendar.MONTH) + 1);
        paramMap.put("day", calendar.get(Calendar.DAY_OF_MONTH));
        paramMap.put("hour", calendar.get(Calendar.HOUR));
        paramMap.put("minute", calendar.get(Calendar.MINUTE));
        paramMap.put("second", calendar.get(Calendar.SECOND));
        paramMap.put("timestamp", String.valueOf(date.getTime()));
        return paramMap;
    }
}
