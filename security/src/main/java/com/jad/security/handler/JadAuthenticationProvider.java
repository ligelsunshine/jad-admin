/*
 * Copyright (c) 2021-2023, jad (cxxwl96@sina.com).
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

import com.jad.common.entity.User;
import com.jad.common.service.UserService;
import com.jad.security.entity.AuthUser;
import com.jad.security.service.AuthorityPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义认证
 *
 * @author cxxwl96
 * @since 2023/4/10 22:43
 */

@Component
public class JadAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityPermissionService authorityPermissionService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 表单提交的username和password
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        final User user = userService.getByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("用户名或密码错误");
        }
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("凭证为空");
        } else if (!userService.matchesUserPassword(username, password, user.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误");
        } else {
            // 生成UserDetail并返回
            final List<GrantedAuthority> userAuthority = authorityPermissionService.getUserAuthority(user.getId());
            final AuthUser authUser = new AuthUser(user.getId(), user.getUsername(), user.getPassword(), userAuthority);
            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authUser,
                authentication.getCredentials(), authUser.getAuthorities());
            result.setDetails(authentication.getDetails());
            return result;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
