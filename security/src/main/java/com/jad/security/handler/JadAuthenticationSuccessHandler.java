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

import com.jad.common.constant.RedisConst;
import com.jad.common.lang.Result;
import com.jad.common.utils.JsonUtil;
import com.jad.common.utils.JwtUtil;
import com.jad.common.utils.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证成功处理器
 *
 * @author cxxwl96
 * @since 2021/6/20 19:55
 */
@Component
public class JadAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        final ServletOutputStream outputStream = response.getOutputStream();

        // 生成jwt
        final String token = jwtUtil.generateToken(authentication.getName());
        // 放到header里面
        response.setHeader(jwtUtil.getHeader(), token);
        // 放到redis里面，过期时间为jwt过期时间，需要从ms转为s
        redisUtil.set(RedisConst.SECURITY_USER_AUTHENTICATE_TOKEN_KEY_PREFIX + authentication.getName(), token,
            jwtUtil.getExpireMilliseconds() / 1000);

        final Result<?> result = Result.success(200, "登录成功", token);
        outputStream.write(JsonUtil.toJSONString(result).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}

