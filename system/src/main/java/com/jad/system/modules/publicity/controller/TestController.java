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

package com.jad.system.modules.publicity.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.lang.Result;
import com.jad.common.service.UserService;
import com.jad.security.annotations.PermitAuth;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * TestController
 *
 * @author cxxwl96
 * @since 2021/6/18 22:41
 */
@Api(tags = "公共接口 - 测试接口")
@RestController
@RequestMapping("/pub/test")
@PermitAuth
public class TestController extends BaseController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户密码加密")
    @GetMapping("/encoder")
    public Result<?> encoder(String username, String password) {
        return Result.success("加密成功", userService.encodeUserPassword(username, password));
    }

    @ApiOperation("yaml配置信息加密")
    @GetMapping("/encrypt")
    public Result<?> encrypt(@RequestParam(defaultValue = "cxxwl96@sina.com") String salt, @RequestParam String text) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        try {
            return Result.success("加密成功", encryptor.encrypt(text));
        } catch (Exception exception) {
            return Result.failed("加密失败: " + exception.getMessage());
        }
    }

    @ApiOperation("yaml配置信息解密")
    @GetMapping("/decrypt")
    public Result<?> decrypt(@RequestParam(defaultValue = "cxxwl96@sina.com") String salt, @RequestParam String text) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        try {
            return Result.success("解密成功", encryptor.decrypt(text));
        } catch (Exception exception) {
            return Result.failed("解密失败: " + exception.getMessage());
        }
    }
}
