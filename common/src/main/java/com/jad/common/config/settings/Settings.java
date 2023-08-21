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

import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统设置
 *
 * @author cxxwl96
 * @since 2023/8/19 22:38
 */
@Slf4j
@Configuration
public class Settings {
    // 系统设置字段
    private static final SystemSettings SYSTEM_SETTINGS = new SystemSettings();

    // 系统设置锁
    private static final Object SETTING_LOCK = new Object();

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
            save(null);
        } else {
            // 从配置文件读取到内存
            refresh();
        }
    }

    /**
     * 保存配置到配置文件
     */
    public void save(@Nullable SystemSettings settings) {
        synchronized (SETTING_LOCK) {
            if (settings != null) {
                BeanUtil.copyProperties(settings, SYSTEM_SETTINGS);
            }
            String json = JSON.toJSONString(SYSTEM_SETTINGS);
            FileUtil.writeUtf8String(json, file);
        }
    }

    /**
     * 从配置文件读取到内存
     */
    public void refresh() {
        synchronized (SETTING_LOCK) {
            String json = FileUtil.readUtf8String(file);
            Settings settings = JSON.parseObject(json, Settings.class);
            BeanUtil.copyProperties(settings, SYSTEM_SETTINGS);
        }
    }

    /**
     * 获取系统设置
     *
     * @return 系统设置
     */
    public SystemSettings getSystemSettings() {
        return SYSTEM_SETTINGS;
    }
}
