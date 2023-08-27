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
import com.jad.common.exception.BadRequestException;
import com.jad.common.utils.RedisUtil;
import com.jad.common.utils.RegexUtil;
import com.jad.sms.config.SmsConfig;
import com.jad.sms.model.Sms;
import com.jad.sms.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executor;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 邮箱服务
 *
 * @author cxxwl96
 * @since 2023/8/23 21:52
 */
@Slf4j
@Service
public class EmailSmsService implements SmsService<Sms> {
    // Redis中存放验证码的key
    private static final String CODE_KEY_PREFIX = RedisConst.SECURITY_VERIFY_KEY + "EMAIL-";

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Resource(name = "asyncSendSms")
    private Executor asyncSendSms;

    /**
     * 发送短讯
     *
     * @param destination 目的地：手机号、邮箱
     * @return 返回结果
     */
    @Override
        public Sms sendSms(String destination) {
        // 验证码key
        final String codeKey = UUID.randomUUID().toString();
        // 验证码
        final String codeValue = RegexUtil.randomIntegerString(6);
        // 将Base64验证码存入Redis
        long timeout = smsConfig.getTimeout();
        redisUtil.set(CODE_KEY_PREFIX + codeKey, codeValue, timeout);

        // 异步发送验证码
        asyncSendSms.execute(() -> dealSendSms(codeValue, destination));

        return new Sms().setCodeKey(codeKey).setCodeValue(codeValue).setTimeout(timeout);
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
        // 从redis获取验证码并验证
        final String redisCodeValue = (String) redisUtil.get(CODE_KEY_PREFIX + codeKey);
        if (codeKey == null || !codeValue.equalsIgnoreCase(redisCodeValue)) {
            return false;
        }
        // 删除redis中的验证码
        redisUtil.del(CODE_KEY_PREFIX + codeKey);
        return true;
    }

    /**
     * 异步发送验证码
     *
     * @param codeValue 验证码
     * @param destination 目的地：手机号、邮箱
     */
    private void dealSendSms(String codeValue, String destination) {
        // validateDestination(destination);
        // 发送邮箱验证码
        Context context = new Context(); // 引入Template的Context
        // 设置模板中的变量（分割验证码）
        context.setVariable("verifyCode", Arrays.asList(codeValue.split("")));
        context.setVariable("timeout", formatTimout(smsConfig.getTimeout()));
        context.setVariable("sign", smsConfig.getSign());
        // 第一个参数为模板的名称(html不用写全路径)
        String process = templateEngine.process("/EmailVerificationCode.html", context); // 这里不用写全路径
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(smsConfig.getSubject()); // 邮件的标题
            helper.setFrom(smsConfig.getUsername()); // 发送者
            helper.setTo(destination); // 接收者
            helper.setSentDate(new Date()); // 时间
            helper.setText(process, true); // 第二个参数true表示这是一个html文本
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            log.error("Error sending email message. {}", e.getMessage(), e);
            throw new BadRequestException("发送验证码失败");
        }
    }

    private void validateDestination(String destination) {
        // 校验邮箱
        if (StrUtil.isBlank(destination)) {
            throw new BadRequestException("请输入邮箱地址");
        }
        if (!destination.matches("^\\w+@(\\w+\\.)+(\\w+)$")) {
            throw new BadRequestException("邮箱格式不正确");
        }
    }

    private String formatTimout(long timeout) {
        StringBuilder format = new StringBuilder();
        while (timeout > 0) {
            if (timeout > 3600) {
                format.append(timeout / 3600).append("小时");
                timeout %= 3600;
            } else if (timeout > 60) {
                format.append(timeout / 60).append("分钟");
                timeout %= 60;
            } else {
                format.append(timeout).append("秒");
                break;
            }
        }
        return format.toString();
    }
}
