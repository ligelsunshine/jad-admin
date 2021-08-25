/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service;

import com.jad.common.base.form.SearchForm;
import com.jad.common.base.service.BaseService;
import com.jad.common.entity.Role;
import com.jad.common.enums.Status;
import com.jad.common.lang.SearchResult;

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
    boolean updateById(Role role);

    /**
     * 分页查询角色
     * - 角色级别降序
     * - 查询角色等级比当前用户角色最高级别低的角色
     *
     * @param searchForm 查询表单
     * @return 角色列表
     */
    SearchResult<Role> getPageList(SearchForm searchForm);

    /**
     * 修改状态
     * - 修改的角色等级不能高于当前用户角色的最高级别
     *
     * @param id 角色ID
     * @param status 状态
     * @return 是否修改成功
     */
    boolean updateStatus(String id, Status status);
}
