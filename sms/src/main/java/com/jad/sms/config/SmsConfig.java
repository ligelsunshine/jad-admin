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

package com.jad.sms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import lombok.Data;

/**
 * 短讯配置
 *
 * @author cxxwl96
 * @since 2023/8/19 13:05
 */
@Data
@Configuration
public class SmsConfig {
    // 验证码过期时间（单位：s）
    @Value("${jad.sms.timeout}")
    private long timeout;

    @Value("${jad.sms.mail.host}")
    private String host;

    @Value("${jad.sms.mail.username}")
    private String username;

    @Value("${jad.sms.mail.password}")
    private String password;

    @Value("${jad.sms.mail.default-encoding}")
    private String defaultEncoding;

    @Value("${jad.sms.mail.subject}")
    private String subject;

    @Value("${jad.sms.mail.sign}")
    private String sign;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding(defaultEncoding);
        return mailSender;
    }

    @Bean
    public Executor asyncSendSms(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 1.核心线程池大小
        executor.setCorePoolSize(2);
        // 2.最大线程池大小
        executor.setMaxPoolSize(5);
        // 3.队列大小
        executor.setQueueCapacity(200);
        // 4.线程名称
        executor.setThreadNamePrefix("async-send-sms-thread-");
        // 5.拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
