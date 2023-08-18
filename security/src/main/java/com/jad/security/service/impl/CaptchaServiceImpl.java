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

package com.jad.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Producer;
import com.jad.common.constant.RedisConst;
import com.jad.common.utils.RedisUtil;
import com.jad.security.model.Captcha;
import com.jad.security.service.CaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import lombok.SneakyThrows;
import sun.misc.BASE64Encoder;

/**
 * CaptchaServiceImpl
 *
 * @author cxxwl96
 * @since 2023/8/18 23:07
 */
@Component
public class CaptchaServiceImpl implements CaptchaService {
    // yaml中配置的验证码过期时间
    @Value("${jad.system.auth-config.captcha-timeout}")
    private long captchaTimeout;

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
    @Override
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
        redisUtil.set(RedisConst.SECURITY_LOGIN_CAPTCHA_KEY_PREFIX + codeKey, codeValue, captchaTimeout);

        return new Captcha().setCodeKey(codeKey)
            .setCodeValue(codeValue)
            .setCodeImageOutputStream(outputStream)
            .setCodeImage(codeImage);
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
        final String redisCodeValue = (String) redisUtil.get(RedisConst.SECURITY_LOGIN_CAPTCHA_KEY_PREFIX + codeKey);
        if (StringUtils.isBlank(codeKey) || StringUtils.isBlank(codeValue) || !codeValue.equalsIgnoreCase(
            redisCodeValue)) {
            return false;
        }
        // 删除redis中的验证码
        redisUtil.del(RedisConst.SECURITY_LOGIN_CAPTCHA_KEY_PREFIX + codeKey);
        return true;
    }
}
