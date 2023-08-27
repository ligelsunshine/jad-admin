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
import com.jad.security.annotations.PermitAuth;
import com.jad.security.model.LoginForm;
import com.jad.security.model.RegisterForm;
import com.jad.security.service.AuthService;
import com.jad.sms.model.Captcha;
import com.jad.sms.service.impl.CaptchaSmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cn.hutool.core.map.MapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 身份认证相关接口
 *
 * @author cxxwl96
 * @since 2021/6/20 12:13
 */
@Api(tags = "公共接口 - 身份认证相关接口")
@RestController
@RequestMapping("/auth")
@PermitAuth
public class AuthController extends BaseController {

    @Autowired
    private CaptchaSmsService captchaSmsService;

    @Autowired
    private AuthService authService;

    @ApiOperation("获取图片验证码")
    @GetMapping("/captcha")
    public Result<?> captcha() {
        Captcha captcha = captchaSmsService.generate();
        final Map<Object, Object> resultMap = MapUtil.builder()
            .put("codeKey", captcha.getCodeKey())
            .put("codeImage", captcha.getCodeImage())
            .build();
        return Result.success(resultMap);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<?> login(LoginForm dto, HttpServletRequest request) {
        // 目的是为了在swagger中显示这个接口
        return null;
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public Result<?> logout(HttpServletRequest request) {
        // 目的是为了在swagger中显示这个接口
        return null;
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<?> register(HttpServletRequest request, @RequestBody @Valid RegisterForm form) {
        if (authService.register(form)) {
            return Result.success("注册成功", form.getUsername());
        }
        return Result.failed("注册失败");
    }
}
