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

package com.jad.sms.service;

import com.jad.sms.model.Sms;

/**
 * 短讯服务
 *
 * @param <T> 短讯结果
 * @author cxxwl96
 * @since 2023/8/23 22:06
 */
public interface SmsService<T extends Sms> {
    /**
     * 发送短讯
     *
     * @param destination 目的地：手机号、邮箱
     * @return 返回结果
     */
    T sendSms(String destination);

    /**
     * 验证短讯信息
     *
     * @param codeKey 短讯的key
     * @param codeValue 短讯的value
     * @return 是否验证通过
     */
    boolean validate(String codeKey, String codeValue);
}
