/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.Menu;
import com.jad.common.entity.Role;
import com.jad.common.entity.RoleMenu;
import com.jad.common.entity.User;
import com.jad.common.entity.UserRole;
import com.jad.common.mapper.UserMapper;
import com.jad.common.service.MenuService;
import com.jad.common.service.RoleMenuService;
import com.jad.common.service.RoleService;
import com.jad.common.service.UserRoleService;
import com.jad.common.service.UserService;
import com.jad.common.utils.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 系统用户服务类
 *
 * @author cxxwl96
 * @since 2021-06-18
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    // yaml中配置的超级管理员ID
    @Value("${jad.system.role.administrator-id}")
    private String administratorId;

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
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    @Override
    public List<Role> getRoles(String userId) {
        // 获取roleId
        final List<String> roleIds = userRoleService.lambdaQuery()
            .select(UserRole::getRoleId)
            .eq(UserRole::getUserId, userId)
            .list()
            .stream()
            .map(UserRole::getRoleId)
            .collect(Collectors.toList());
        List<Role> roles = new ArrayList<>();
        if (roleIds.size() > 0) {
            // 获取role
            roles = roleService.lambdaQuery().in(Role::getId, roleIds).list();
        }
        return roles;
    }

    /**
     * 获取当前用户角色
     *
     * @return 用户角色列表
     */
    @Override
    public List<Role> getRoles() {
        final User authUser = getCurrentAuthUser();
        return getRoles(authUser.getId());
    }

    /**
     * 获取用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表字符串，用逗号分隔
     */
    @Override
    public String getUserAuthority(String userId) {
        String authority;
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
            // 获取菜单权限
            final List<Menu> menus = menuService.getUserMenuList(userId);
            // 生成security权限列表字符串
            final List<String> roleAuthorities = roles.stream()
                .map(role -> "ROLE_" + role.getCode())
                .collect(Collectors.toList());
            final List<String> menuAuthorities = menus.stream()
                .map(Menu::getPermissions)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
            roleAuthorities.addAll(menuAuthorities);
            authority = String.join(",", roleAuthorities);

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
        // 获取userId
        final List<String> userIds = userRoleService.lambdaQuery()
            .select(UserRole::getUserId)
            .eq(UserRole::getRoleId, roleId)
            .list()
            .stream()
            .map(UserRole::getUserId)
            .collect(Collectors.toList());
        if (userIds.size() == 0) {
            return;
        }
        // 获取username
        final List<String> usernames = this.lambdaQuery()
            .select(User::getUsername)
            .in(User::getId, userIds)
            .list()
            .stream()
            .map(User::getUsername)
            .collect(Collectors.toList());
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
        final List<String> roleIds = roleMenuService.lambdaQuery()
            .select(RoleMenu::getRoleId)
            .eq(RoleMenu::getMenuId, menuId)
            .list()
            .stream()
            .map(RoleMenu::getRoleId)
            .collect(Collectors.toList());
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

    /**
     * 是否拥有超级管理员身份
     *
     * @return 是否拥有超级管理员身份
     */
    @Override
    public boolean hasAdministrator() {
        // 当前登录用户
        final User user = this.getCurrentAuthUser();
        // 过滤是否拥有超级管理员身份
        final Optional<Role> administrator = this.getRoles(user.getId())
            .stream()
            .filter(role -> role.getId().equalsIgnoreCase(administratorId))
            .findFirst();
        return administrator.isPresent();
    }
}
