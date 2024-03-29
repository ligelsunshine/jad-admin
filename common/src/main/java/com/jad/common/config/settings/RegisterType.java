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

package com.jad.common.config.settings;

import lombok.Data;

/**
 * 系统设置 - 安全设置 - 注册方式
 *
 * @author cxxwl96
 * @since 2023/8/20 00:37
 */
@Data
public class RegisterType {
    // 正常注册
    private Boolean normal = false;

    // 手机注册
    private Boolean phoneVerificationCode = false;

    // 邮箱注册
    private Boolean emailVerificationCode = false;
}
