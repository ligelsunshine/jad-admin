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

package com.jad.security.enums;

/**
 * 注册方式枚举
 *
 * @author cxxwl96
 * @since 2023/8/18 22:09
 */
public enum RegisterTypeEnum {
    NORMAL("正常注册，仅需账号和密码"),
    PHONE_VERIFICATION_CODE("手机验证码注册，需手机号、密码、手机验证码"),
    EMAIL_VERIFICATION_CODE("邮箱验证码注册，需邮箱、密码、邮箱验证码");

    private final String des;

    RegisterTypeEnum(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }
}
