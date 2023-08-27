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

package com.jad.sms.service.impl;

import com.jad.common.constant.RedisConst;
import com.jad.common.utils.RedisUtil;
import com.jad.sms.config.SmsConfig;
import com.jad.sms.model.Sms;
import com.jad.sms.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 手机短信服务
 *
 * @author cxxwl96
 * @since 2023/8/23 23:33
 */
@Service
public class PhoneSmsService implements SmsService<Sms> {
    // Redis中存放验证码的key
    private static final String CODE_KEY_PREFIX = RedisConst.SECURITY_VERIFY_KEY + "PHONE-";

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送短讯
     *
     * @param destination 目的地：手机号、邮箱
     * @return 返回结果
     */
    @Override
    public Sms sendSms(String destination) {
        return null;
    }

    /**
     * 验证短讯信息
     *
     * @param codeKey 短讯的key
     * @param codeValue 短讯的value
     * @return 是否验证通过
     */
    @Override
    public boolean validate(String codeKey, String codeValue) {
        return false;
    }
}
