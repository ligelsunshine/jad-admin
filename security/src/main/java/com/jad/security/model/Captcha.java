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

package com.jad.security.model;

import java.io.ByteArrayOutputStream;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Captcha
 *
 * @author cxxwl96
 * @since 2023/8/18 23:08
 */
@ApiModel(value = "图形验证码")
@Data
@Accessors(chain = true)
public class Captcha {
    private String codeKey;

    private String codeValue;

    private ByteArrayOutputStream codeImageOutputStream;

    // base64 Image
    private String codeImage;

    // 验证码过期时间（单位：s）
    private long captchaTimeout;

}