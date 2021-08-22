/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jad.common.entity.Role;
import com.jad.common.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-18
 */
public interface UserService extends IService<User> {

    /**
     * 根据username获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

    /**
     * 获取用户角色
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    List<Role> getRoles(String userId);

    /**
     * 获取用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表字符串，用逗号分隔
     */
    String getUserAuthority(String userId);

    /**
     * 清除用户授权信息
     *
     * @param username 用户名
     */
    void clearUserAuthorityByUsername(String username);

    /**
     * 清除用户授权信息
     *
     * @param roleId 角色ID
     */
    void clearUserAuthorityByRoleId(String roleId);

    /**
     * 清除用户授权信息
     *
     * @param menuId 权限菜单ID
     */
    void clearUserAuthorityByMenuId(String menuId);

    /**
     * 获取当前登录的用户
     *
     * @return 用户
     */
    User getCurrentAuthUser();

    /**
     * 是否拥有超级管理员身份
     *
     * @return 是否拥有超级管理员身份
     */
    boolean hasAdministrator();
}
