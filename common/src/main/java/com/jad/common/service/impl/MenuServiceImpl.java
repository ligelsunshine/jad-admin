/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jad.common.entity.Menu;
import com.jad.common.entity.RoleMenu;
import com.jad.common.entity.User;
import com.jad.common.entity.UserRole;
import com.jad.common.mapper.MenuMapper;
import com.jad.common.service.MenuService;
import com.jad.common.service.RoleMenuService;
import com.jad.common.service.UserRoleService;
import com.jad.common.service.UserService;
import com.jad.common.utils.TreeUtil;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 获取用户菜单权限列表
     *
     * @param userId 用户ID
     * @return 用户菜单权限
     */
    @Override
    public List<Menu> getMenuList(String userId) {
        List<Menu> menus = new ArrayList<>();
        // 获取roleId
        final List<String> roleIds = userRoleService.lambdaQuery()
            .select(UserRole::getRoleId)
            .eq(UserRole::getUserId, userId)
            .list()
            .stream()
            .map(UserRole::getRoleId)
            .collect(Collectors.toList());
        if (roleIds.size() == 0) {
            return menus;
        }
        // 获取menuId
        final List<String> menuIds = roleMenuService.lambdaQuery()
            .select(RoleMenu::getMenuId)
            .in(RoleMenu::getRoleId, roleIds)
            .list()
            .stream()
            .map(RoleMenu::getMenuId)
            .collect(Collectors.toList());
        if (menuIds.size() == 0) {
            return menus;
        }
        // 获取menu
        return this.lambdaQuery().in(Menu::getId, menuIds).list();
    }

    /**
     * 获取当前登录用户菜单权限列表
     *
     * @return 用户菜单权限
     */
    public List<Menu> getMenuList() {
        // 当前登录的用户
        User curUser = userService.getCurrentAuthUser();
        // 如果是超级管理员，则返回所有菜单
        if (userService.hasAdministrator()) {
            return this.list();
        } else {
            return this.getMenuList(curUser.getId());
        }
    }

    /**
     * 获取当前登录用户菜单权限树
     *
     * @return 用户菜单权限
     */
    public List<Menu> getMenuTree() {
        final List<Menu> menuList = this.getMenuList();
        return TreeUtil.stringTree(menuList);
    }
}
