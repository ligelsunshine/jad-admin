/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.security.handler;

import com.jad.common.constant.RedisConst;
import com.jad.common.utils.JwtUtil;
import com.jad.common.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出登录逻辑
 *
 * @author cxxwl96
 * @since 2021/6/26 00:55
 */
@Component
public class JadLogoutHandler implements LogoutHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String token = request.getHeader(jwtUtil.getHeader());
        final Claims claims = jwtUtil.getClaims(token);
        if (claims!=null){
            final String username = claims.getSubject();
            // 从Redis中移除token
            redisUtil.del(RedisConst.SECURITY_USER_AUTHENTICATE_TOKEN_KEY_PREFIX + username);
        }
        // security清除认证信息
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

    }
}
