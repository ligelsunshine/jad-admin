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

package com.jad.generator.common;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jad.common.exception.BadRequestException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
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

    public FreemarkerGenerator(String name) {
        this.name = name;
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
    public void processFile(Object paramObj, String outputPath) {
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

    /**
     * 生成字符串
     *
     * @param paramObj 模板参数
     * @return 字符串
     */
    public String process(Object paramObj) {
        Writer out = null;
        boolean success = true;
        String error = null;
        try {
            final Template temp = this.getTemplate();
            out = new StringWriter();
            temp.process(paramObj, out);
        } catch (TemplateException | IOException e) {
            throw new BadRequestException(e.getMessage());
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (NullPointerException | IOException e) {
                success = false;
                error = e.getMessage();
                e.printStackTrace();
            }
        }
        if (!success) {
            throw new BadRequestException(error);
        }
        return out.toString();
    }

    private void createOutputPath(String path) {
        final File file = new File(path);
        FileUtil.mkParentDirs(file);
    }
}
