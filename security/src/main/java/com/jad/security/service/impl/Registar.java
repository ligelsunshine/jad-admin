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

package com.jad.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.jad.common.entity.Role;
import com.jad.common.entity.User;
import com.jad.common.exception.BadRequestException;
import com.jad.common.exception.CaptchaException;
import com.jad.common.service.RoleService;
import com.jad.common.service.UserService;
import com.jad.security.model.RegisterForm;
import com.jad.security.service.CaptchaService;
import com.jad.security.service.IRegistar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Registar
 *
 * @author cxxwl96
 * @since 2023/8/18 22:44
 */
@Slf4j
@Component
public class Registar implements IRegistar {
    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /**
     * 正常注册，一般不开放给普通用户
     *
     * @param form 注册表单
     */
    @Override
    public void normalRegist(RegisterForm form) {
        // 校验验证码
        if (!captchaService.validate(form.getCodeKey(), form.getCodeValue())) {
            throw new CaptchaException("验证码错误");
        }
        // 获取默认角色
        Role defaultRole = roleService.getDefaultRole();
        // 创建用户
        User user = new User().setUsername(form.getUsername())
            .setPassword(form.getPassword())
            .setRoleIds(CollUtil.newArrayList(defaultRole.getId()))
            .setOrigin(form.getType());
        // 添加用户
        if (!userService.save(user)) {
            log.error("Failed to register user. User: {}", JSON.toJSONString(user));
            throw new BadRequestException("注册失败");
        }
        log.info("User {}[{}] registered successfully. User: {}", user.getUsername(), user.getId(),
            JSON.toJSONString(user));
    }

    /**
     * 手机验证码注册
     *
     * @param form 注册表单
     */
    @Override
    public void phoneVerificationCodeRegist(RegisterForm form) {
        // TODO register
    }

    /**
     * 邮箱验证码注册
     *
     * @param form 注册表单
     */
    @Override
    public void emailVerificationCodeRegist(RegisterForm form) {
        // TODO register
    }
}
