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

package com.jad.security.handler;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jad.common.lang.Result;
import com.jad.common.utils.JsonUtil;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未认证
 * jwt身份验证入口点
 * 用于进行匿名用户访问资源时无权限的处理（目的为了覆盖security未认证返回登录页面）
 *
 * @author cxxwl96
 * @since 2021/6/21 21:35
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 从JwtAuthenticationFilter转发的request中获取信息
        String msg = (String) request.getAttribute("msg");
        msg = StringUtils.isBlank(msg) ? "未登录认证" : msg;

        final ServletOutputStream outputStream = response.getOutputStream();
        final Result<?> result = Result.failed(HttpServletResponse.SC_UNAUTHORIZED, msg, null);
        outputStream.write(JsonUtil.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}