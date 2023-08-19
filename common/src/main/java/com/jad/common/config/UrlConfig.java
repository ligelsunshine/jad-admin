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

package com.jad.common.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * UrlUtil
 *
 * @author cxxwl96
 * @since 2021/6/20 20:38
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jad.security")
public class UrlConfig {

    // 首页
    public static final String HOME_URL = "/";

    // 错误页面
    public static final String ERROR_URL = "/error";

    // 获取验证码URL
    public static final String CAPTCHA_URL = "/auth/captcha";

    // 登录URL
    public static final String LOGIN_URL = "/auth/login";

    // 登出URL
    public static final String LOGOUT_URL = "/auth/logout";

    // 注册URL
    public static final String REGISTER = "/auth/register";

    private static final String[] URL_WHITE_LIST = {
        UrlConfig.HOME_URL, UrlConfig.ERROR_URL, "/auth/**", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js",
        "/webSocket/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/*/api-docs"
    };

    // 白名单
    private String urlWhiteList;

    /**
     * 获取URL白名单
     *
     * @return URL白名单
     */
    public String[] getUrlWhiteList() {
        if (StringUtils.isBlank(urlWhiteList)) {
            return URL_WHITE_LIST;
        }
        final Set<String> whiteList = Arrays.stream(URL_WHITE_LIST).collect(Collectors.toSet());
        whiteList.addAll(Arrays.asList(urlWhiteList.split(",")));
        // 由于会出现[Ljava.lang.Object; cannot be cast to [Ljava.lang.String;错误
        // 这里使用传统方式转换为String[]
        String[] whiteUrls = new String[whiteList.size()];
        int i = 0;
        for (String url : whiteList) {
            if (StringUtils.isBlank(url)) {
                continue;
            }
            whiteUrls[i++] = url;
        }
        return whiteUrls;
    }
}
