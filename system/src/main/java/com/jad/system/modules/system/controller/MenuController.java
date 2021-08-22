/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.system.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.entity.Menu;
import com.jad.common.lang.Result;
import com.jad.common.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private MenuService menuService;

    @ApiOperation("添加菜单权限")
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Menu menu) {
        if (menuService.save(menu)) {
            return Result.success("添加成功");
        }
        return Result.failed("添加失败");
    }

    @ApiOperation("删除菜单权限")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id, boolean includeSelf) {
        if (menuService.removeTree(id, includeSelf)) {
            return Result.success("删除成功");
        }
        return Result.failed("删除失败");
    }

    @ApiOperation("修改菜单权限")
    @PutMapping("/update")
    public Result update(@RequestBody @Valid Menu menu) {
        if (menuService.updateById(menu)) {
            return Result.success("修改成功");
        }
        return Result.failed("修改失败");
    }

    @ApiOperation("获取单个菜单权限")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        final Menu menu = menuService.getById(id);
        return Result.success(menu);
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
