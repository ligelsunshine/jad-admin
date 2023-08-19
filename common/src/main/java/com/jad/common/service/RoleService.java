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
import com.jad.common.entity.Role;
import com.jad.common.enums.Status;
import com.jad.common.lang.SearchResult;

import java.util.List;

/**
 * 角色服务接口类
 *
 * @author cxxwl96
 * @since 2021/8/22 14:34
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 添加角色
     * - 添加的角色等级不能高于当前用户角色的最高级别
     *
     * @param role 角色
     * @return 是否添加成功
     */
    @Override
    boolean save(Role role);

    /**
     * 删除角色
     * - 删除的角色等级不能高于当前用户角色的最高级别
     *
     * @param id 角色ID
     * @return 是否删除成功
     */
    boolean removeById(String id);

    /**
     * 修改角色
     * - 修改的角色等级不能高于当前用户角色的最高级别
     *
     * @param role 角色
     * @return 是否修改成功
     */
    @Override
    boolean updateById(Role role);

    /**
     * 获取角色列表
     * - 角色级别降序
     * - 查询角色等级比当前用户角色最高级别低的角色
     *
     * @return 角色列表
     */
    List<Role> getList();

    /**
     * 分页查询角色
     * - 角色级别降序
     * - 查询角色等级比当前用户角色最高级别低的角色
     *
     * @param searchForm 查询表单
     * @return 角色列表
     */
    @Override
    SearchResult<Role> getPageList(SearchForm searchForm);

    /**
     * 修改默认角色
     * - 角色列表中只能存在一个默认角色
     *
     * @param id 角色ID
     * @return 是否修改成功
     */
    boolean updateDefaultRole(String id);

    /**
     * 修改状态
     * - 修改的角色等级不能高于当前用户角色的最高级别
     *
     * @param id 角色ID
     * @param status 状态
     * @return 是否修改成功
     */
    boolean updateStatus(String id, Status status);

    /**
     * 获取超级管理员角色
     *
     * @return 是否拥有超级管理员身份
     */
    Role getAdministrator();

    /**
     * 获取默认角色
     *
     * @return 默认角色
     */
    Role getDefaultRole();
}
