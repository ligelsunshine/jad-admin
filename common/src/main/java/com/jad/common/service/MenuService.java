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

import com.jad.common.base.service.TreeService;
import com.jad.common.entity.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-18
 */
public interface MenuService extends TreeService<Menu> {

    /**
     * 添加菜单
     *
     * @param menu 菜单
     * @return 是否添加成功
     */
    @Override
    boolean save(Menu menu);

    /**
     * 添加菜单权限按钮
     *
     * @param pid 父级菜单
     * @param modelName 菜单名称
     * @param authPrefix 菜单权限前缀
     * @return 是否添加成功
     */
    boolean saveAuthButton(String pid, String modelName, String authPrefix);

    /**
     * 删除子树
     *
     * @param id 子树根节点id
     * @param includeSelf 是否包含子树根节点
     * @return 是否删除成功
     */
    boolean removeTree(String id, boolean includeSelf);

    /**
     * 修改菜单
     *
     * @param menu 菜单
     * @return 是否修改成功
     */
    @Override
    boolean updateById(Menu menu);

    /**
     * 获取用户菜单权限列表
     *
     * @param userId 用户ID
     * @return 用户菜单权限
     */
    List<Menu> getUserMenuList(String userId);

    /**
     * 获取当前登录用户菜单权限列表
     *
     * @return 用户菜单权限
     */
    List<Menu> getUserMenuList();

    /**
     * 获取当前登录用户菜单权限树
     *
     * @return 用户菜单权限
     */
    List<Menu> getUserMenuTree();

    /**
     * 清除用户缓存的菜单列表
     */
    void clearUserMenuList();

    /**
     * 清除用户缓存的菜单树
     */
    void clearUserMenuTree();
}
