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

import com.jad.common.enums.UserOrigin;
import com.jad.common.exception.BadRequestException;
import com.jad.security.model.RegisterForm;
import com.jad.security.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AuthServiceImpl
 *
 * @author cxxwl96
 * @since 2023/8/18 22:02
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private Registar registar;

    /**
     * 用户注册
     *
     * @param form 注册表单
     * @return 是否注册成功
     */
    @Override
    public boolean register(RegisterForm form) {
        UserOrigin userOrigin = form.getType();
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
}
