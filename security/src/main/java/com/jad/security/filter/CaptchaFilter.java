/*
 * Copyright (c) 2021-2021, jad (cxxwl96@sina.com).
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

package com.jad.security.filter;

import com.jad.common.config.UrlConfig;
import com.jad.common.exception.CaptchaException;
import com.jad.common.utils.RedisUtil;
import com.jad.security.handler.JadAuthenticationFailureHandler;
import com.jad.sms.service.impl.CaptchaSmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码过滤器
 *
 * @author cxxwl96
 * @since 2021/6/20 20:35
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    // 参数名：验证码key
    private static final String CODE_KEY = "codeKey";

    // 参数名：验证码value
    private static final String CODE_VALUE = "codeValue";

    @Autowired
    private CaptchaSmsService captchaSmsService;

    @Autowired
    JadAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        final String uri = request.getRequestURI();
        // 只处理登录请求
        if (UrlConfig.LOGIN_URL.equals(uri)) {
            try {
                // 校验验证码
                validateCaptcha(request);
            } catch (CaptchaException e) {
                // 如果不正确，跳转到认证失败处理器
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 校验验证码
     *
     * @param request request
     */
    private void validateCaptcha(HttpServletRequest request) {
        final String codeKey = request.getParameter(CODE_KEY);
        final String codeValue = request.getParameter(CODE_VALUE);
        if (!captchaSmsService.validate(codeKey, codeValue)) {
            throw new CaptchaException("验证码错误");
        }
    }
}
