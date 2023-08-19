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
package com.jad.common.config.settings;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * 系统设置
 *
 * @author cxxwl96
 * @since 2023/8/19 22:38
 */
@Slf4j
@Data
@Configuration("systemSettings")
public class SystemSettings {
    /*########################### Begin 系统设置字段 ###########################*/
    private Security security;
    /*###########################   End 系统设置字段 ###########################*/
    @Value("${jad.system.setting.file-location}")
    private transient String fileLocation;

    private transient File file;

    @PostConstruct
    public void init() {
        log.info("Initializing System Settings");
        file = new File(fileLocation);
        log.info("System setting file location: {}", file.getAbsoluteFile());
        if (!file.exists() || StrUtil.isBlank(FileUtil.readUtf8String(file))) {
            try {
                FileUtil.mkParentDirs(file);
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Failed to create system setting file. File: " + file.getAbsoluteFile());
            }
            // 保存配置到配置文件
            save();
        } else {
            // 从配置文件读取到内存
            refresh();
        }
    }

    /**
     * 保存配置到配置文件
     */
    public void save() {
        SystemSettings thisSettings = getSystemSettings();
        String json = JSON.toJSONString(thisSettings);
        FileUtil.writeUtf8String(json, file);
    }

    /**
     * 从配置文件读取到内存
     */
    public void refresh() {
        SystemSettings thisSettings = getSystemSettings();
        String json = FileUtil.readUtf8String(file);
        SystemSettings settings = JSON.parseObject(json, SystemSettings.class);
        BeanUtil.copyProperties(settings, thisSettings);
    }

    private SystemSettings getSystemSettings() {
        return SpringUtil.getBean("systemSettings", SystemSettings.class);
    }
}
