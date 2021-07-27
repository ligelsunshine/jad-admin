/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.Menu;
import com.jad.common.entity.RoleMenu;
import com.jad.common.entity.User;
import com.jad.common.entity.UserRole;
import com.jad.common.mapper.MenuMapper;
import com.jad.common.service.MenuService;
import com.jad.common.service.RoleMenuService;
import com.jad.common.service.UserRoleService;
import com.jad.common.service.UserService;
import com.jad.common.utils.RedisUtil;
import com.jad.common.utils.TreeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
    private RedisUtil redisUtil;

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
        List<Menu> menuList = null;
        // 在Redis中缓存菜单信息
        if (redisUtil.hHasKey(RedisConst.SYSTEM_MENU_LIST, curUser.getUsername())) {
            final String menuListJson = (String) redisUtil.hget(RedisConst.SYSTEM_MENU_LIST, curUser.getUsername());
            menuList = JSONArray.parseArray(menuListJson, Menu.class);
        } else {
            // 如果是超级管理员，则返回所有菜单
            if (userService.hasAdministrator()) {
                menuList = this.list();
            } else {
                menuList = this.getMenuList(curUser.getId());
            }
            // 序列化menuList，缓存在Redis中
            final String menuListJson = JSONObject.toJSONString(menuList);
            redisUtil.hset(RedisConst.SYSTEM_MENU_LIST, curUser.getUsername(), menuListJson);
        }
        return menuList;
    }

    /**
     * 获取当前登录用户菜单权限树
     *
     * @return 用户菜单权限
     */
    public List<Menu> getMenuTree() {
        // 获取当前登录用户菜单权限列表
        final List<Menu> menuList = this.getMenuList();
        // 生成菜单树
        final List<Menu> menuTree = TreeUtil.stringTree(menuList);
        // 排序
        return sortMenuTree(menuTree);
    }

    /**
     * 菜单树排序
     *
     * @param menuTree 菜单树
     * @return 菜单树
     */
    private List<Menu> sortMenuTree(List<Menu> menuTree) {
        final List<Menu> menus = menuTree.stream()
            .sorted(Comparator.comparingInt(Menu::getOrderNo))
            .collect(Collectors.toList());
        menus.forEach(menu -> {
            if (menu.getChildren() != null && menu.getChildren().size() > 0) {
                menu.setChildren(sortMenuTree(menu.getChildren()));
            }
        });
        return menus;
    }
}
