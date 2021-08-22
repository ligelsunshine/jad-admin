/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jad.common.config.UrlConfig;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.User;
import com.jad.common.service.UserService;
import com.jad.common.utils.JwtUtil;
import com.jad.common.utils.RedisUtil;
import com.jad.security.service.impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;

/**
 * Jwt认证过滤器
 *
 * @author cxxwl96
 * @since 2021/6/21 20:37
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UrlConfig urlConfig;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 过滤白名单
     *
     * @param request 请求
     * @return URL白名单
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 过滤白名单
        return Arrays.stream(urlConfig.getUrlWhiteList())
            .anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        final String token = request.getHeader(jwtUtil.getHeader());
        // 如果token为null，则放行请求，让security自行拦截
        if (StringUtils.isBlank(token)) {
            chain.doFilter(request, response);
            return;
        }
        final Claims claims = jwtUtil.getClaims(token);
        if (claims == null) {
            request.setAttribute("msg", "登录令牌异常");
            chain.doFilter(request, response);
            return;
        }
        if (jwtUtil.isExpired(claims)) {
            request.setAttribute("msg", "登录已过期");
            chain.doFilter(request, response);
            return;
        }

        // 获取用户的权限等信息
        final String username = claims.getSubject();

        // 从redis中获取用户登录的token
        final String redisToken = (String) redisUtil.get(
            RedisConst.SECURITY_USER_AUTHENTICATE_TOKEN_KEY_PREFIX + username);
        // 若token不存在，则表示该用户登录已过期
        if (StringUtils.isBlank(redisToken)) {
            request.setAttribute("msg", "登录已过期");
            chain.doFilter(request, response);
            return;
        }
        // 若token与header中token不相同，则表示已经在别处登录
        if (!redisToken.equals(token)) {
            request.setAttribute("msg", "您已在别处登录");
            chain.doFilter(request, response);
            return;
        }

        final User user = userService.getByUsername(username);

        // 将用户的权限等信息保存到security中
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            user.getUsername(), user.getPassword(), userDetailsService.getUserAuthority(user.getId()));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }
}
