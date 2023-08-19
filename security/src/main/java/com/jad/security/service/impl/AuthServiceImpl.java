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

import cn.hutool.core.util.ReflectUtil;
import com.jad.common.config.settings.RegisterType;
import com.jad.common.config.settings.SystemSettings;
import com.jad.common.enums.UserOrigin;
import com.jad.common.exception.BadRequestException;
import com.jad.security.model.RegisterForm;
import com.jad.security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * AuthServiceImpl
 *
 * @author cxxwl96
 * @since 2023/8/18 22:02
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private Registar registar;

    @Autowired
    private SystemSettings systemSettings;

    /**
     * 用户注册
     *
     * @param form 注册表单
     * @return 是否注册成功
     */
    @Override
    public boolean register(RegisterForm form) {
        UserOrigin userOrigin = form.getType();
        // 校验用户注册方式是否被允许
        checkUserOrigin(userOrigin);
        // 校验两次密码是否一致
        if (!form.getPassword().equals(form.getRePassword())) {
            throw new BadRequestException("两次密码不一致");
        }
        switch (userOrigin) {
            case NORMAL: // 正常注册，一般不开放给普通用户
                registar.normalRegist(form);
                break;
            case PHONE_VERIFICATION_CODE: // 手机验证码注册
                registar.phoneVerificationCodeRegist(form);
                break;
            case EMAIL_VERIFICATION_CODE: // 邮箱验证码注册
                registar.emailVerificationCodeRegist(form);
                break;
            default:
                throw new BadRequestException("无效的注册类型");
        }
        return true;
    }

    /**
     * 校验用户注册方式是否被允许
     *
     * @param userOrigin 用户账号来源
     */
    private void checkUserOrigin(UserOrigin userOrigin) {
        // 获取系统设置的注册类型开关
        RegisterType registerType = systemSettings.getSecurity().getRegisterType();
        // 判断注册类型是否系统设置里面打开开关
        for (Field field : registerType.getClass().getDeclaredFields()) {
            // 只需要字段类型为Boolean
            if (!field.getType().equals(Boolean.class)) {
                continue;
            }
            // 找到对应的注册类型
            if (userOrigin.getKeyCode().equals(field.getName())) {
                boolean enable;
                try {
                    // 获取开关的值
                    enable = (Boolean) ReflectUtil.getFieldValue(registerType, field);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new BadRequestException("系统错误: IllegalAccess");
                }
                // 是否允许此注册方式
                if (!enable) {
                    throw new BadRequestException("不支持的注册类型：" + userOrigin.getKeyCode());
                }
                return;
            }
        }
        throw new BadRequestException("不支持的注册类型：" + userOrigin.getKeyCode());
    }
}
