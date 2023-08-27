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

import com.google.code.kaptcha.Producer;
import com.jad.common.constant.RedisConst;
import com.jad.common.utils.RedisUtil;
import com.jad.sms.config.SmsConfig;
import com.jad.sms.model.Captcha;
import com.jad.sms.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import lombok.SneakyThrows;
import sun.misc.BASE64Encoder;

/**
 * 图形验证码服务
 *
 * @author cxxwl96
 * @since 2023/8/18 23:07
 */
@Service
public class CaptchaSmsService implements SmsService<Captcha> {
    // Redis中存放验证码的key
    private static final String CODE_KEY_PREFIX = RedisConst.SECURITY_VERIFY_KEY + "CAPTCHA-";

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 验证码生成器
     */
    @Autowired
    private Producer producer;

    /**
     * 生成图片验证码
     *
     * @return 图片验证码
     */
    @SneakyThrows
    public Captcha generate() {
        // 验证码key
        final String codeKey = UUID.randomUUID().toString();
        // 验证码
        final String codeValue = producer.createText();

        // 生成Base64验证码
        // 生成验证码图片
        final BufferedImage image = producer.createImage(codeValue);
        // 将验证码图片写入到output流
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 将output流转为base64图片码
        final BASE64Encoder encoder = new BASE64Encoder();

        final String codeImage = "data:image/jpeg;base64," + encoder.encode(outputStream.toByteArray());

        // 将Base64验证码存入Redis
        long timeout = smsConfig.getTimeout();
        redisUtil.set(CODE_KEY_PREFIX + codeKey, codeValue, timeout);

        Captcha captcha = new Captcha();
        captcha.setCodeKey(codeKey);
        captcha.setCodeValue(codeValue);
        captcha.setTimeout(timeout);
        captcha.setCodeImageOutputStream(outputStream);
        captcha.setCodeImage(codeImage);
        return captcha;
    }

    /**
     * 发送短讯
     *
     * @param destination 目的地：手机号、邮箱
     * @return 返回结果
     */
    @Override
    public Captcha sendSms(String destination) {
        throw new UnsupportedOperationException();
    }

    /**
     * 验证图片验证码
     *
     * @param codeKey 验证码的key
     * @param codeValue 验证码的value
     * @return 是否验证通过
     */
    @Override
    public boolean validate(String codeKey, String codeValue) {
        // 从redis获取验证码并验证
        final String redisCodeValue = (String) redisUtil.get(CODE_KEY_PREFIX + codeKey);
        if (codeKey == null || !codeValue.equalsIgnoreCase(redisCodeValue)) {
            return false;
        }
        // 删除redis中的验证码
        redisUtil.del(CODE_KEY_PREFIX + codeKey);
        return true;
    }
}
