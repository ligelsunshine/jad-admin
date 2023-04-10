/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.security.config;

import com.jad.common.config.UrlConfig;
import com.jad.security.filter.CaptchaFilter;
import com.jad.security.filter.JwtAuthenticationFilter;
import com.jad.security.handler.JadAuthenticationFailureHandler;
import com.jad.security.handler.JadAuthenticationProvider;
import com.jad.security.handler.JadAuthenticationSuccessHandler;
import com.jad.security.handler.JadLogoutHandler;
import com.jad.security.handler.JadLogoutSuccessHandler;
import com.jad.security.handler.JwtAccessDeniedHandler;
import com.jad.security.handler.JwtAuthenticationEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置类
 *
 * @author cxxwl96
 * @since 2021/6/20 13:15
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JadSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UrlConfig urlConfig;

    @Autowired
    private JadAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private JadAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private JadLogoutSuccessHandler jadLogoutSuccessHandler;

    @Autowired
    private JadLogoutHandler jadLogoutHandler;

    @Autowired
    private CaptchaFilter captchaFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // 自定义认证
    @Autowired
    private JadAuthenticationProvider authenticationProvider;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf()
            .disable()
            // 登录配置
            .formLogin()
            .loginProcessingUrl(UrlConfig.LOGIN_URL)
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)

            // 登出配置
            .and()
            .logout()
            .logoutUrl(UrlConfig.LOGOUT_URL)
            .addLogoutHandler(jadLogoutHandler)
            .logoutSuccessHandler(jadLogoutSuccessHandler)

            // 禁用session
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // 配置拦截规则
            .and()
            .authorizeRequests()
            .antMatchers(urlConfig.getUrlWhiteList())
            .permitAll()
            .anyRequest()
            .authenticated()

            // 异常处理器
            .and()
            .exceptionHandling()
            // 未认证处理
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            // 未授权处理
            .accessDeniedHandler(jwtAccessDeniedHandler)

            // 配置自定义过滤器
            .and()
            // jwt认证过滤器
            .addFilter(jwtAuthenticationFilter())
            // 在验证用户名、密码之前校验验证码
            .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

}
