/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.security.handler;

import com.jad.common.lang.Result;
import com.jad.common.utils.JsonUtil;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未授权
 * jwt拒绝访问处理器
 *
 * @author cxxwl96
 * @since 2021/6/21 21:40
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
        throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        final ServletOutputStream outputStream = response.getOutputStream();
        final Result result = Result.failed(HttpServletResponse.SC_FORBIDDEN, "权限不足", e.getMessage());
        outputStream.write(JsonUtil.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
