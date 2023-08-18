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

package com.jad.security.service;

import com.jad.security.model.RegisterForm;

/**
 * IRegistar
 *
 * @author cxxwl96
 * @since 2023/8/18 22:50
 */
public interface IRegistar {

    /**
     * 正常注册，一般不开放给普通用户
     *
     * @param form 注册表单
     */
    void normalRegist(RegisterForm form);

    /**
     * 手机验证码注册
     *
     * @param form 注册表单
     */
    void phoneVerificationCodeRegist(RegisterForm form);

    /**
     * 邮箱验证码注册
     *
     * @param form 注册表单
     */
    void emailVerificationCodeRegist(RegisterForm form);
}
