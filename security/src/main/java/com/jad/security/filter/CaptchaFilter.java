/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jad.common.config.UrlConfig;
import com.jad.common.constant.RedisConst;
import com.jad.common.exception.CaptchaException;
import com.jad.common.utils.RedisUtil;
import com.jad.security.handler.JadAuthenticationFailureHandler;

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
        // 从redis获取验证码并验证
        final String redisCodeValue = (String) redisUtil.get(RedisConst.SECURITY_LOGIN_CAPTCHA_KEY_PREFIX + codeKey);
        if (StringUtils.isBlank(codeKey) || StringUtils.isBlank(codeValue) || !codeValue.equalsIgnoreCase(
            redisCodeValue)) {
            throw new CaptchaException("验证码错误");
        }
        // 删除redis中的验证码
        redisUtil.del(RedisConst.SECURITY_LOGIN_CAPTCHA_KEY_PREFIX + codeKey);
    }
}
