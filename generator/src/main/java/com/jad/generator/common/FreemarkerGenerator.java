/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.common;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jad.common.exception.BadRequestException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

/**
 * freemarker代码生成工具
 *
 * @author cxxwl96
 * @since 2021/9/11 21:27
 */
@Slf4j
public class FreemarkerGenerator {

    // 模板文件名。格式：[resource path]/[文件名].[生成文件后缀名].ftl
    // 例如：templates/back/Entity.java.ftl
    private final String name;

    // 输出文件路径
    private final String outputPath;

    public FreemarkerGenerator(String name, String outputPath) {
        this.name = name;
        this.outputPath = outputPath;
    }

    /**
     * 获取模板
     *
     * @return 模板
     */
    public Template getTemplate() {
        Template temp;
        try {
            Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
            cfg.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);
            temp = cfg.getTemplate(this.name);
        } catch (IOException | NullPointerException e) {
            throw new BadRequestException(e.getMessage());
        }
        return temp;
    }

    /**
     * 生成文件
     *
     * @param paramObj 模板参数
     */
    public void process(Object paramObj) {
        try {
            createOutputPath(outputPath);
            final Template temp = this.getTemplate();
            final FileWriter fw = new FileWriter(outputPath);
            temp.process(paramObj, fw);
            log.info("Generate freemarker file: 'file [{}]'", outputPath);
        } catch (TemplateException | IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private void createOutputPath(String path) {
        final File file = new File(path);
        FileUtil.mkParentDirs(file);
    }
}