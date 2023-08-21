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

package com.jad.system.modules.publicity.controller;

import com.jad.common.config.settings.Settings;
import com.jad.common.lang.Result;
import com.jad.security.annotations.PermitAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 公共接口 - 系统设置相关接口
 *
 * @author cxxwl96
 * @since 2023/8/20 14:03
 */
@Api(tags = "公共接口 - 系统设置相关接口")
@RestController
@RequestMapping("/pub/setting")
@PermitAuth
public class PublicSettingController {
    @Autowired
    private Settings settings;

    @ApiOperation("获取注册方式")
    @GetMapping("/getRegisterType")
    public Result<?> getAll() {
        return Result.success(settings.getSystemSettings().getSecurity().getRegisterType());
    }
}
