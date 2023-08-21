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

package com.jad.system.modules.system.controller;

import com.jad.common.config.settings.Settings;
import com.jad.common.config.settings.SystemSettings;
import com.jad.common.lang.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统管理 - 系统设置相关接口
 *
 * @author cxxwl96
 * @since 2023/8/20 14:03
 */
@Api(tags = "系统管理 - 系统设置相关接口")
@RestController
@RequestMapping("/sys/setting")
public class SystemSettingController {
    @Autowired
    private Settings settings;

    @ApiOperation("保存系统设置")
    @PutMapping("/save")
    public Result<?> save(@RequestBody @Valid SystemSettings systemSettings) {
        settings.save(systemSettings);
        return Result.success("保存成功");
    }

    @ApiOperation("获取所有系统设置")
    @GetMapping("/getAll")
    public Result<?> getAll() {
        return Result.success(settings.getSystemSettings());
    }
}
