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

package com.jad.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户账号来源
 *
 * @author cxxwl96
 * @since 2023/8/19 00:19
 */
@Getter
@AllArgsConstructor
public enum UserOrigin {
    UNKNOWN(0, "unknown", "未知来源"),
    ADMIN_SAVE(1, "adminSave", "管理员添加"),
    NORMAL(2, "normal", "正常注册，需账号、密码、图形验证码"),
    PHONE_VERIFICATION_CODE(3, "phoneVerificationCode", "手机验证码注册，需手机号、密码、手机验证码"),
    EMAIL_VERIFICATION_CODE(4, "emailVerificationCode", "邮箱验证码注册，需邮箱、密码、邮箱验证码");

    @EnumValue
    @JsonValue
    private final int index;

    // 编码，用于系统设置字段
    private final String keyCode;

    private final String des;
}
