/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.service.impl;

import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.lang.Result;
import com.jad.common.utils.SystemUtil;
import com.jad.generator.common.FreemarkerGenerator;
import com.jad.generator.config.PathConfig;
import com.jad.generator.entity.Generator;
import com.jad.generator.enums.FieldType;
import com.jad.generator.mapper.GeneratorMapper;
import com.jad.generator.model.FieldSchema;
import com.jad.generator.model.GenerateConfig;
import com.jad.generator.model.Item;
import com.jad.generator.model.Model;
import com.jad.generator.model.Module;
import com.jad.generator.service.GeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.system.SystemPropsKeys;

/**
 * GeneratorServiceImpl
 *
 * @author cxxwl96
 * @since 2021/9/11 23:16
 */
@Service
public class GeneratorServiceImpl extends BaseServiceImpl<GeneratorMapper, Generator> implements GeneratorService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取module
     *
     * @return module
     */
    @Override
    public List<Module> getModule() {
        final String userDir = System.getProperty(SystemPropsKeys.USER_DIR);
        final Module module = this.deepModule(userDir);
        final ArrayList<Module> list = new ArrayList<>();
        list.add(module);
        return list;
    }

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
     * 预览数据库表DDL
     *
     * @param model model
     * @return 预览结果
     */
    @Override
    public Result viewTable(Model model) {
        final Map<String, Object> paramMap = getParamMap(model);
        final String tableSql = new FreemarkerGenerator("templates/db/table.sql.ftl").process(paramMap);
        return Result.success(null, tableSql);
    }

    /**
     * 预览后端代码
     *
     * @param model model
     * @param config config
     * @return 预览结果
     */
    @Override
    public Result viewBack(Model model, GenerateConfig config) {
        List<Map<String, String>> viewList = new ArrayList<>();
        getItems(model, config).forEach(item -> {
            final String content = new FreemarkerGenerator(item.getTempPath()).process(item.getParamMap());
            final Map<String, String> map = new HashMap<>();
            map.put("path", item.getOutputPath());
            map.put("content", content);
            viewList.add(map);
        });
        return Result.success(viewList);
    }

    /**
     * 生成后端代码
     *
     * @param model model
     */
    @Override
    public void generateBack(Model model, GenerateConfig config) {
        getItems(model, config).forEach(item -> {
            new FreemarkerGenerator(item.getTempPath()).processFile(item.getParamMap(), item.getOutputPath());
        });
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
     * 生成默认参数
     *
     * @param model model
     * @return 参数
     */
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

    /**
     * 获取项目module结构
     *
     * @param modulePath modulePath
     * @return module结构
     */
    private Module deepModule(String modulePath) {
        final boolean isModule = FileUtil.exist(modulePath + File.separator + "pom.xml");
        if (!isModule) {
            return null;
        }
        final File file = new File(modulePath);
        final Module module = new Module();
        module.setKey(UUID.randomUUID().toString().replace("-", ""));
        module.setAbsolutePath(file.getAbsolutePath());
        module.setName(file.getName());

        final String[] list = file.list();
        if (list == null) {
            return null;
        }
        List<Module> modules = new ArrayList<>();
        for (String name : list) {
            String path = modulePath + File.separator + name;
            if (FileUtil.isDirectory(path) && FileUtil.exist(path + File.separator + "pom.xml")) {
                modules.add(deepModule(path));
            }
        }
        module.setChildren(modules);
        return module;
    }

    /**
     * 获取生成数据
     *
     * @param model model
     * @param config config
     * @return 生成数据
     */
    private List<Item> getItems(Model model, GenerateConfig config) {
        final PathConfig pathConfig = new PathConfig(model.getModule());

        List<Item> itemList = new ArrayList<>();

        if (config.isEntity()) {
            // 添加entity生成数据
            final Map<String, Object> paramMap = getParamMap(model);
            paramMap.put("package", pathConfig.getEntityPackage());
            final Item item = new Item();
            item.setTempPath("templates/back/entity.java.ftl");
            item.setOutputPath(pathConfig.getEntityPath() + "/" + model.getBigHump() + ".java");
            item.setParamMap(paramMap);
            itemList.add(item);
            // 添加enum生成数据
            final List<FieldSchema> fieldSchemas = model.getFieldSchema();
            if (CollUtil.isEmpty(fieldSchemas)) {
                final Map<String, Object> enumParamMap = getParamMap(model);
                enumParamMap.put("package", pathConfig.getEnumPackage());
                fieldSchemas.forEach(fieldSchema -> {
                    if (fieldSchema.getType() == FieldType.ENUM) {
                        enumParamMap.put("enumParam", fieldSchema);
                        final Item enumItem = new Item();
                        enumItem.setTempPath("templates/back/enum.java.ftl");
                        enumItem.setOutputPath(pathConfig.getEnumPath() + "/" + fieldSchema.getBigHump() + ".java");
                        enumItem.setParamMap(enumParamMap);
                        itemList.add(enumItem);
                    }
                });
            }
        }
        if (config.isMapper()) {
            // 添加mapper生成数据
            final Map<String, Object> paramMap = getParamMap(model);
            paramMap.put("package", pathConfig.getMapperPackage());
            final Item item = new Item();
            item.setTempPath("templates/back/mapper.java.ftl");
            item.setOutputPath(pathConfig.getMapperPath() + "/" + model.getBigHump() + "Mapper.java");
            item.setParamMap(paramMap);
            itemList.add(item);
        }
        if (config.isMapperXml()) {
            // 添加mapperXml生成数据
            final Map<String, Object> paramMap = getParamMap(model);
            paramMap.put("package", pathConfig.getMapperPackage());
            final Item item = new Item();
            item.setTempPath("templates/back/mapper.xml.ftl");
            item.setOutputPath(pathConfig.getMapperXmlPath() + "/" + model.getBigHump() + "Mapper.xml");
            item.setParamMap(paramMap);
            itemList.add(item);
        }
        if (config.isService()) {
            // 添加service生成数据
            final Map<String, Object> paramMap = getParamMap(model);
            paramMap.put("package", pathConfig.getServicePackage());
            final Item item = new Item();
            item.setTempPath("templates/back/service.java.ftl");
            item.setOutputPath(pathConfig.getServicePath() + "/" + model.getBigHump() + "Service.java");
            item.setParamMap(paramMap);
            itemList.add(item);
        }
        if (config.isServiceImpl()) {
            // 添加serviceImpl生成数据
            final Map<String, Object> paramMap = getParamMap(model);
            paramMap.put("package", pathConfig.getServiceImplPackage());
            final Item item = new Item();
            item.setTempPath("templates/back/serviceImpl.java.ftl");
            item.setOutputPath(pathConfig.getServiceImplPath() + "/" + model.getBigHump() + "ServiceImpl.java");
            item.setParamMap(paramMap);
            itemList.add(item);
        }
        if (config.isServiceImpl()) {
            // 添加controller生成数据
            final Map<String, Object> paramMap = getParamMap(model);
            paramMap.put("package", pathConfig.getControllerPackage());
            final Item item = new Item();
            item.setTempPath("templates/back/controller.java.ftl");
            item.setOutputPath(pathConfig.getControllerPath() + "/" + model.getBigHump() + "Controller.java");
            item.setParamMap(paramMap);
            itemList.add(item);
        }
        return itemList;
    }
}
