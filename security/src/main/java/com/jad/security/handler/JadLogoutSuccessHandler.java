/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.security.handler;

import com.jad.common.lang.Result;
import com.jad.common.utils.JsonUtil;
import com.jad.common.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出登录成功处理器
 *
 * @author cxxwl96
 * @since 2021/6/25 22:35
 */
@Component
public class JadLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        final ServletOutputStream outputStream = response.getOutputStream();

        // 清除header
        response.setHeader(jwtUtil.getHeader(), "");

        final Result<?> result = Result.success(200, "退出成功", null);
        outputStream.write(JsonUtil.toJSONString(result).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}
