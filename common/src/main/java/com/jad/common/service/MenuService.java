/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service;

import com.jad.common.base.service.BaseService;
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
public interface MenuService extends BaseService<Menu> {

    /**
     * 添加菜单
     *
     * @param menu 菜单
     * @return 是否添加成功
     */
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
