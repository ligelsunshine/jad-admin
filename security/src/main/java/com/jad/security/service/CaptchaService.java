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

import com.jad.security.model.Captcha;

/**
 * 图形验证码
 *
 * @author cxxwl96
 * @since 2023/8/18 23:07
 */
public interface CaptchaService {
    /**
     * 生成图片验证码
     *
     * @return 图片验证码
     */
    Captcha generate();

    /**
     * 验证图片验证码
     *
     * @param codeKey 验证码的key
     * @param codeValue 验证码的value
     * @return 是否验证通过
     */
    boolean validate(String codeKey, String codeValue);
}
