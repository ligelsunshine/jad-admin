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

package com.jad.security.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * 自定义鉴权 - 接口
 *
 * @author cxxwl96
 * @since 2021/10/19 21:40
 */
public interface AuthorityPermissionService {
    /**
     * 判断是否有权限
     *
     * @param authority 权限
     * @return 是否有权限
     */
    boolean hasAuthority(String authority);

    /**
     * 获取用户授权信息
     *
     * @param userId 用户ID
     * @return 用户授权信息
     */
    List<GrantedAuthority> getUserAuthority(String userId);
}
