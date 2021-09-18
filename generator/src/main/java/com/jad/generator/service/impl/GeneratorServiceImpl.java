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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;

/**
 * GeneratorServiceImpl
 *
 * @author cxxwl96
 * @since 2021/9/11 23:16
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 生成数据库表DDL
     *
     * @param model model
     */
    @Override
    public void generateTable(Model model) {
        final Map<String, Object> paramMap = getParamMap(model);
        final String tableSql = new FreemarkerGenerator("templates/db/table.sql.ftl").process(paramMap);
        jdbcTemplate.execute(tableSql);
    }

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
        if (model.getGenerateConfig().isMapper()) {
            // 生成mapper
            generateMapper(model, pathConfig);
        }
        if (model.getGenerateConfig().isMapperXml()) {
            // 生成mapperXml
            generateMapperXml(model, pathConfig);
        }
        if (model.getGenerateConfig().isService()) {
            // 生成service
            generateService(model, pathConfig);
            // 生成serviceImpl
            generateServiceImpl(model, pathConfig);
        }
        if (model.getGenerateConfig().isController()) {
            // 生成controller
            generateController(model, pathConfig);
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
        final Map<String, Object> paramMap = getParamMap(model);
        paramMap.put("package", pathConfig.getEntityPackage());
        String tempPath = "templates/back/entity.java.ftl";
        String outputPath = pathConfig.getEntityPath() + "/" + model.getBigHump() + ".java";
        new FreemarkerGenerator(tempPath).processFile(paramMap, outputPath);
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
                String tempPath = "templates/back/enum.java.ftl";
                String outputPath = pathConfig.getEnumPath() + "/" + fieldSchema.getBigHump() + ".java";
                new FreemarkerGenerator(tempPath).processFile(paramMap, outputPath);
            }
        });
    }

    /**
     * 生成生成mapper
     *
     * @param model model
     * @param pathConfig pathConfig
     */
    private void generateMapper(Model model, PathConfig pathConfig) {
        final Map<String, Object> paramMap = getParamMap(model);
        paramMap.put("package", pathConfig.getMapperPackage());
        String tempPath = "templates/back/mapper.java.ftl";
        String outputPath = pathConfig.getMapperPath() + "/" + model.getBigHump() + "Mapper.java";
        new FreemarkerGenerator(tempPath).processFile(paramMap, outputPath);
    }

    /**
     * 生成生成mapperXml
     *
     * @param model model
     * @param pathConfig pathConfig
     */
    private void generateMapperXml(Model model, PathConfig pathConfig) {
        final Map<String, Object> paramMap = getParamMap(model);
        paramMap.put("namespace", pathConfig.getMapperPackage());
        String tempPath = "templates/back/mapper.xml.ftl";
        String outputPath = pathConfig.getMapperXmlPath() + "/" + model.getBigHump() + "Mapper.xml";
        new FreemarkerGenerator(tempPath).processFile(paramMap, outputPath);
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
        String tempPath = "templates/back/service.java.ftl";
        String outputPath = pathConfig.getServicePath() + "/" + model.getBigHump() + "Service.java";
        new FreemarkerGenerator(tempPath).processFile(paramMap, outputPath);
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
        String tempPath = "templates/back/serviceImpl.java.ftl";
        String outputPath = pathConfig.getServiceImplPath() + "/" + model.getBigHump() + "ServiceImpl.java";
        new FreemarkerGenerator(tempPath).processFile(paramMap, outputPath);
    }

    /**
     * 生成controller
     *
     * @param model model
     * @param pathConfig pathConfig
     */
    private void generateController(Model model, PathConfig pathConfig) {
        final Map<String, Object> paramMap = getParamMap(model);
        paramMap.put("package", pathConfig.getControllerPackage());
        String tempPath = "templates/back/controller.java.ftl";
        String outputPath = pathConfig.getControllerPath() + "/" + model.getBigHump() + "Controller.java";
        new FreemarkerGenerator(tempPath).processFile(paramMap, outputPath);
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
