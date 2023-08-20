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
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
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
            // 禁用CSRF（防止伪造的跨域攻击）
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
            .authorizeRequests() // 对请求执行认证与授权
            .antMatchers(urlConfig.getAllPermitUrls()) // 匹配白名单
            .permitAll() // 不需要通过认证即允许访问
            .anyRequest() // 除以上配置过的请求路径以外的所有请求路径
            .authenticated() // 要求是已经通过认证的

            // 异常处理器
            .and()
            .exceptionHandling()
            // 未认证处理
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            // 未授权处理
            .accessDeniedHandler(jwtAccessDeniedHandler)
            // 配置自定义过滤器
            .and()
            // 在验证用户名、密码之前校验验证码
            .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
            // jwt认证过滤器
            .addFilter(jwtAuthenticationFilter());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

}
