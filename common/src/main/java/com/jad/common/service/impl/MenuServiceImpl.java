/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.Menu;
import com.jad.common.entity.RoleMenu;
import com.jad.common.entity.Tree;
import com.jad.common.entity.User;
import com.jad.common.entity.UserRole;
import com.jad.common.enums.MenuType;
import com.jad.common.exception.BadRequestException;
import com.jad.common.lang.Result;
import com.jad.common.mapper.MenuMapper;
import com.jad.common.service.MenuService;
import com.jad.common.service.RoleMenuService;
import com.jad.common.service.UserRoleService;
import com.jad.common.service.UserService;
import com.jad.common.utils.RedisUtil;
import com.jad.common.utils.ValidatorUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

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
     * 添加菜单
     *
     * @param menu 菜单
     * @return 是否添加成功
     */
    @Override
    public boolean save(Menu menu) {
        // 菜单实体校验
        validMenu(menu, false);
        final boolean success = super.save(menu);
        clearUserMenuList();
        clearUserMenuTree();
        return success;
    }

    /**
     * 删除子树
     *
     * @param id 子树根节点id
     * @param includeSelf 是否包含子树根节点
     * @return 是否删除成功
     */
    @Override
    public boolean removeTree(String id, boolean includeSelf) {
        boolean success = false;
        List<Menu> list;
        final List<Menu> menuTree = getUserMenuTree();
        final Tree<Menu> tree = new Tree<>(menuTree);
        if (includeSelf) {
            list = tree.getSubList(id);
        } else {
            list = tree.getChildrenList(id);
        }
        if (CollUtil.isNotEmpty(list)) {
            final List<String> ids = list.stream().map(Menu::getId).collect(Collectors.toList());
            success = super.removeByIds(ids);
        }
        // 清空缓存
        clearUserMenuList();
        clearUserMenuTree();
        return success;
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单
     * @return 是否修改成功
     */
    @Override
    public boolean updateById(Menu menu) {
        // 菜单实体校验
        validMenu(menu, true);
        final boolean success = super.updateById(menu);
        clearUserMenuList();
        clearUserMenuTree();
        return success;
    }

    /**
     * 获取用户菜单权限列表
     *
     * @param userId 用户ID
     * @return 用户菜单权限
     */
    @Override
    public List<Menu> getUserMenuList(String userId) {
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
        return this.lambdaQuery().in(Menu::getId, menuIds).orderByAsc(Menu::getCreateTime).list();
    }

    /**
     * 获取角色菜单权限列表
     *
     * @param roleId 角色ID
     * @return 用户菜单权限
     */
    @Override
    public List<Menu> getRoleMenuList(String roleId) {
        List<Menu> menus = new ArrayList<>();
        // 获取菜单id
        final List<String> menuIds = roleMenuService.lambdaQuery()
            .eq(RoleMenu::getRoleId, roleId)
            .list()
            .stream()
            .map(RoleMenu::getMenuId)
            .collect(Collectors.toList());
        if (menuIds.size() == 0) {
            return menus;
        }
        // 获取菜单
        return this.lambdaQuery().in(Menu::getId, menuIds).list();
    }

    /**
     * 获取当前登录用户菜单权限列表
     *
     * @return 用户菜单权限
     */
    @Override
    public List<Menu> getUserMenuList() {
        // 当前登录的用户
        User curUser = userService.getCurrentAuthUser();
        List<Menu> menuList;
        if (redisUtil.hHasKey(RedisConst.SYSTEM_USER_MENU_LIST, curUser.getUsername())) {
            // 从Redis中获取菜单列表
            final String menuListJson = (String) redisUtil.hget(RedisConst.SYSTEM_USER_MENU_LIST,
                curUser.getUsername());
            menuList = JSONArray.parseArray(menuListJson, Menu.class);
        } else {
            // 如果是超级管理员，则返回所有菜单
            if (userService.hasAdministrator()) {
                menuList = this.lambdaQuery().orderByAsc(Menu::getCreateTime).list();
            } else {
                menuList = this.getUserMenuList(curUser.getId());
            }
            // 序列化菜单列表，缓存在Redis中
            final String menuListJson = JSONObject.toJSONString(menuList);
            redisUtil.hset(RedisConst.SYSTEM_USER_MENU_LIST, curUser.getUsername(), menuListJson);
        }
        // 若pId为空白，则设置pId为null，使用方便前端在编辑根节点时不展示父级
        menuList.forEach(menu -> {
            if (StrUtil.isBlank(menu.getPId())) {
                menu.setPId(null);
            }
        });
        return menuList;
    }

    /**
     * 获取当前登录用户菜单权限树
     *
     * @return 用户菜单权限
     */
    @Override
    public List<Menu> getUserMenuTree() {
        // 当前登录的用户
        User curUser = userService.getCurrentAuthUser();
        List<Menu> menuTree;
        if (redisUtil.hHasKey(RedisConst.SYSTEM_USER_MENU_TREE, curUser.getUsername())) {
            // 从Redis中获取菜单树
            final String menuTreeJson = (String) redisUtil.hget(RedisConst.SYSTEM_USER_MENU_TREE,
                curUser.getUsername());
            menuTree = JSONArray.parseArray(menuTreeJson, Menu.class);
        } else {
            // 获取当前登录用户菜单权限列表
            final List<Menu> menuList = this.getUserMenuList();
            // 生成菜单树
            final Tree<Menu> tree = new Tree<>(menuList, null);
            menuTree = tree.getRootTree();

            // 序列化菜单列表，缓存在Redis中
            // 这里不能使用 JSONObject.toJSONString(menuList);
            // 因为会遇到循环引用问题，导致序列化出来的json字符串会出现"$ref"
            final String menuTreeJson = JSON.toJSONString(menuTree, SerializerFeature.DisableCircularReferenceDetect);
            redisUtil.hset(RedisConst.SYSTEM_USER_MENU_TREE, curUser.getUsername(), menuTreeJson);
        }

        return menuTree;
    }

    /**
     * 清除用户缓存的菜单列表
     */
    @Override
    public void clearUserMenuList() {
        final User curUser = userService.getCurrentAuthUser();
        redisUtil.hdel(RedisConst.SYSTEM_USER_MENU_LIST, curUser.getUsername());
    }

    /**
     * 清除用户缓存的菜单树
     */
    @Override
    public void clearUserMenuTree() {
        final User curUser = userService.getCurrentAuthUser();
        redisUtil.hdel(RedisConst.SYSTEM_USER_MENU_TREE, curUser.getUsername());
    }

    /**
     * 菜单实体校验
     *
     * @param menu 菜单
     */
    private void validMenu(Menu menu, boolean isUpdate) {
        // 若是更新则判断id是否存在
        if (isUpdate && StrUtil.isBlank(menu.getId())) {
            throw new BadRequestException(Result.failed("id不能为空"));
        }
        // 分组实体校验
        Class<?> clazzGroup;
        if (menu.getType() == MenuType.DIRECTORY) {
            clazzGroup = Menu.DirectoryValidGroup.class;
        } else if (menu.getType() == MenuType.MENU) {
            clazzGroup = Menu.MenuValidGroup.class;
        } else {
            clazzGroup = Menu.ButtonValidGroup.class;
        }
        ValidatorUtil.validateEntity(menu, clazzGroup);
        // 根目录的路由地址必须以'/'开头，且component为'LAYOUT'
        if (StrUtil.isBlank(menu.getPId()) && menu.getType() == MenuType.DIRECTORY) {
            if (!menu.getPath().startsWith("/")) {
                throw new BadRequestException(Result.failed("根目录的路由地址必须以'/'开头"));
            }
            menu.setComponent("LAYOUT");
        }
        // 若是外链，则需要给frameSrc赋值path
        if (menu.getExternal()) {
            menu.setFrameSrc(menu.getPath());
        }
    }
}
