/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.security.handler;

import com.jad.common.exception.CaptchaException;
import com.jad.common.lang.Result;
import com.jad.common.utils.JsonUtil;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败处理器
 *
 * @author cxxwl96
 * @since 2021/6/20 19:44
 */
@Component
public class JadAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException e) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        String errorMsg = "用户名或密码错误";

        if (e instanceof CaptchaException) {
            errorMsg = e.getMessage();
        }

        final ServletOutputStream outputStream = response.getOutputStream();
        final Result<?> result = Result.failed(HttpServletResponse.SC_BAD_REQUEST, errorMsg, null);
        outputStream.write(JsonUtil.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
