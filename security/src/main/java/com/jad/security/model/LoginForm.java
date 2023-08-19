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

package com.jad.security.model;

import com.jad.security.enums.LoginTypeEnum;

import lombok.Data;

/**
 * 验证码登录DTO
 *
 * @author cxxwl96
 * @since 2021/6/27 23:45
 */
@Data
public class LoginForm {

    // 用户名
    private String username;

    // 密码
    private String password;

    // 登录类型
    private LoginTypeEnum type;

    // 验证码key
    private String codeKey;

    // 验证码value
    private String codeValue;
}
