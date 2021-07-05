/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jad.common.base.entity.BaseEntity;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.*;
import com.jad.common.mapper.UserMapper;
import com.jad.common.service.*;
import com.jad.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据username获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getByUsername(String username) {
        return getOne(new QueryWrapper<User>().eq("username", username));
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    @Override
    public List<Role> getRoles(String userId) {
        final List<String> roleIds = userRoleService.query().select("role_id").eq("user_id", userId).list()
            .stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roles = new ArrayList<>();
        if (roleIds.size() > 0) {
            roles = roleService.query().in("id", roleIds).list();
        }
        return roles;
    }

    /**
     * 获取用户菜单权限
     *
     * @param userId 用户ID
     * @return 用户菜单权限
     */
    @Override
    public List<Menu> getMenus(String userId) {
        List<Menu> menus = new ArrayList<>();
        final List<String> roleIds = this.getRoles(userId).stream().map(BaseEntity::getId).collect(Collectors.toList());
        if (roleIds.size() == 0) {
            return menus;
        }
        final List<String> menuIds = roleMenuService.query().select("menu_id").in("role_id", roleIds).list()
            .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        if (menuIds.size() == 0) {
            return menus;
        }
        return menuService.query().in("id", menuIds).list();
    }

    /**
     * 获取用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表字符串，用逗号分隔
     */
    @Override
    public String getUserAuthority(String userId) {
        String authority = "";
        final User user = this.getById(userId);

        // 在Redis中缓存用户权限信息
        if (redisUtil.hHasKey(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername())) {
            authority = (String) redisUtil.hget(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername());
        } else {
            // 获取角色
            final List<Role> roles = this.getRoles(userId);
            if (roles.size() == 0) {
                return "";
            }
            // 获取权限
            final List<Menu> menus = this.getMenus(userId);
            // 生成security权限列表字符串
            final String roleAuthority =
                roles.stream().map(role -> "ROLE_" + role.getCode()).collect(Collectors.joining(
                    ","));
            final String menuAuthority = menus.stream().map(Menu::getPermissions).collect(Collectors.joining(","));
            authority = authority.concat(roleAuthority).concat(",").concat(menuAuthority);

            redisUtil.hset(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername(), authority);
        }
        return authority;
    }

    /**
     * 清除用户授权信息
     *
     * @param username 用户名
     */
    @Override
    public void clearUserAuthorityByUsername(String username) {
        redisUtil.hdel(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, username);
    }

    /**
     * 清除用户授权信息
     *
     * @param roleId 角色ID
     */
    @Override
    public void clearUserAuthorityByRoleId(String roleId) {
        // 根据角色ID获取所有用户名
        final List<String> userIds = userRoleService.query().select("user_id").eq("role_id", roleId).list()
            .stream().map(UserRole::getUserId).collect(Collectors.toList());
        if (userIds.size() == 0) {
            return;
        }
        final List<String> usernames = this.query().select("username").in("id", userIds).list()
            .stream().map(User::getUsername).collect(Collectors.toList());
        // 清除用户授权信息
        usernames.forEach(this::clearUserAuthorityByUsername);
    }

    /**
     * 清除用户授权信息
     *
     * @param menuId 权限菜单ID
     */
    @Override
    public void clearUserAuthorityByMenuId(String menuId) {
        // 根据权限菜单ID获取角色ID
        final List<String> roleIds = roleMenuService.query().select("role_id").eq("menu_id", menuId).list()
            .stream().map(RoleMenu::getRoleId).collect(Collectors.toList());
        // 清除用户授权信息
        roleIds.forEach(this::clearUserAuthorityByRoleId);
    }

    /**
     * 获取当前登录的用户
     *
     * @return 用户
     */
    @Override
    public User getCurrentAuthUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();
        final User user = this.getByUsername(username);
        user.setPassword(null);
        return user;
    }
}
