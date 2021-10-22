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

package com.jad.security.service.impl;

import com.jad.common.service.UserService;
import com.jad.security.service.AuthorityPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义权限控制
 *
 * @author cxxwl96
 * @since 2021/10/19 21:41
 */
@Service("auth")
public class AuthorityPermissionServiceImpl implements AuthorityPermissionService {
    @Autowired
    private UserService userService;

    /**
     * 判断是否有权限
     *
     * @param authority 权限
     * @return 是否有权限
     */
    @Override
    public final boolean hasAuthority(String authority) {
        if (StrUtil.isBlank(authority)) {
            return false;
        }
        if (userService.hasAdministrator()) {
            return true;
        }
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities == null) {
            return false;
        }
        for (GrantedAuthority authorize : authorities) {
            if (authority.equals(authorize.getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
