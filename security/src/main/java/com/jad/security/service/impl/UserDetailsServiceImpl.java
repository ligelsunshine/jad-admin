/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.security.service.impl;

import com.jad.common.entity.User;
import com.jad.security.entity.AuthUser;
import com.jad.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserDetailServiceImpl
 *
 * @author cxxwl96
 * @since 2021/6/21 22:25
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        final List<GrantedAuthority> userAuthority = getUserAuthority(user.getId());
        return new AuthUser(user.getId(), user.getUsername(), user.getPassword(), userAuthority);
    }

    /**
     * 获取用户权限列表（角色、菜单权限）
     *
     * @param userId userId
     * @return 用户权限列表
     */
    public List<GrantedAuthority> getUserAuthority(String userId) {
        // 获取用户权限列表
        return AuthorityUtils.commaSeparatedStringToAuthorityList(userService.getUserAuthority(userId));
    }
}
