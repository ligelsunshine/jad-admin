/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.system.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.entity.Menu;
import com.jad.common.enums.MenuType;
import com.jad.common.lang.Result;
import com.jad.common.service.MenuService;
import com.jad.common.service.UserService;
import com.jad.common.utils.ValidatorUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统管理 - 菜单权限相关接口
 *
 * @author cxxwl96
 * @since 2021/8/9 23:32
 */
@Api(tags = "系统管理 - 菜单权限相关接口")
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @ApiOperation("添加菜单权限")
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Menu menu) {
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

        final boolean success = menuService.save(menu);
        if (success) {
            return Result.success("添加成功");
        }
        return Result.failed("添加失败");
    }

    @ApiOperation("获取菜单权限")
    @GetMapping("/getMenuList")
    public Result getMenuList() {
        List<Menu> menus = menuService.getMenuList();
        return Result.success(menus);
    }

    @ApiOperation("获取菜单权限树")
    @GetMapping("/getMenuTree")
    public Result getMenuTree() {
        List<Menu> menus = menuService.getMenuTree();
        return Result.success(menus);
    }
}
