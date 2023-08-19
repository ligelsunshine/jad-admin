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

package com.jad.common.service;

import com.jad.common.base.form.SearchForm;
import com.jad.common.base.service.BaseService;
import com.jad.common.entity.Dept;
import com.jad.common.entity.Role;
import com.jad.common.entity.User;
import com.jad.common.lang.SearchResult;
import com.jad.common.model.dto.UserBaseInfoDto;

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
    @Override
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
     * 更新当前登录用户基础信息
     *
     * @param userBaseInfoDto 用户
     * @return 是否更新成功
     */
    boolean updateBaseInfo(UserBaseInfoDto userBaseInfoDto);

    /**
     * 更新当前登录用户的密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 是否更新成功
     */
    boolean updatePassword(String oldPassword, String newPassword);

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
    @Override
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
     * 获取用户部门
     *
     * @param userId 用户ID
     * @return 用户部门
     */
    Dept getDept(String userId);

    /**
     * 获取当前用户部门
     *
     * @return 用户部门
     */
    Dept getDept();

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
     * 是否已认证
     *
     * @return 是否已认证
     */
    boolean Authenticated();

    /**
     * 是否拥有超级管理员身份
     *
     * @return 是否拥有超级管理员身份
     */
    boolean hasAdministrator();

    /**
     * 是否拥有超级管理员身份
     *
     * @param userId 用户ID
     * @return 是否拥有超级管理员身份
     */
    boolean hasAdministrator(String userId);

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
     * 用户密码加密
     *
     * @param username 用户名
     * @param password 密码
     * @return 加密密码
     */
    String encodeUserPassword(String username, String password);

    /**
     * 验证用户密码是否正确
     *
     * @param username 用户名
     * @param password 未加密密码
     * @param encodedPassword 已加密密码
     * @return 用户密码是否正确
     */
    boolean matchesUserPassword(String username, String password, String encodedPassword);
}
