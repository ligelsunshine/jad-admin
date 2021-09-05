/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service;

import com.jad.common.base.form.SearchForm;
import com.jad.common.base.service.BaseService;
import com.jad.common.entity.Role;
import com.jad.common.entity.User;
import com.jad.common.lang.SearchResult;

import java.util.List;

/**
 * 系统用户服务类接口
 *
 * @author cxxwl96
 * @since 2021-06-18
 */
public interface UserService extends BaseService<User> {
    /**
     * 添加用户
     *
     * @param user 用户
     * @return 是否添加成功
     */
    boolean save(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean removeById(String id);

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 是否更新成功
     */
    boolean update(User user);

    /**
     * 获取用户
     *
     * @param id 用户ID
     * @return 用户
     */
    User getById(String id);

    /**
     * 分页获取用户
     *
     * @param searchForm 查询表单
     * @return 分页结果
     */
    SearchResult<User> getPageList(SearchForm searchForm);

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
     * 获取当前用户角色
     *
     * @return 用户角色列表
     */
    List<Role> getRoles();

    /**
     * 获取用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表字符串，用逗号分隔
     */
    String getUserAuthority(String userId);

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

}
