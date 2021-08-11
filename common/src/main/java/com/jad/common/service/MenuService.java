/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface MenuService extends IService<Menu> {

    /**
     * 添加菜单
     *
     * @param menu 菜单
     * @return 是否添加成功
     */
    boolean save(Menu menu);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return 是否删除成功
     */
    boolean removeById(String id);

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
    List<Menu> getMenuList(String userId);

    /**
     * 获取当前登录用户菜单权限列表
     *
     * @return 用户菜单权限
     */
    List<Menu> getMenuList();

    /**
     * 获取当前登录用户菜单权限树
     *
     * @return 用户菜单权限
     */
    List<Menu> getMenuTree();

    /**
     * 清除用户缓存的菜单
     */
    void clearUserMenuList();
}
