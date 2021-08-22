/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.system.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.OrderItem;
import com.jad.common.base.form.SearchForm;
import com.jad.common.entity.Role;
import com.jad.common.lang.Result;
import com.jad.common.lang.SearchResult;
import com.jad.common.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统管理 - 角色相关接口
 *
 * @author cxxwl96
 * @since 2021/8/22 14:33
 */
@Api(tags = "系统管理 - 角色相关接口")
@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("添加系统角色")
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Role role) {
        if (roleService.save(role)) {
            return Result.success("添加成功");
        }
        return Result.failed("添加失败");
    }

    @ApiOperation("删除系统角色")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        if (roleService.removeById(id)) {
            return Result.success("删除成功");
        }
        return Result.failed("删除失败");
    }

    @ApiOperation("修改系统角色")
    @PostMapping("/update")
    public Result update(@RequestBody @Valid Role role) {
        if (roleService.save(role)) {
            return Result.success("修改成功");
        }
        return Result.failed("修改失败");
    }

    @ApiOperation("获取单个系统角色")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        return Result.success(roleService.getById(id));
    }

    @ApiOperation("获取所有系统角色")
    @GetMapping("/get/list")
    public Result getList() {
        return Result.success(roleService.list());
    }

    @ApiOperation("分页获取系统角色")
    @GetMapping("/get/page")
    public Result getListPage(SearchForm searchForm) {
        // 异常默认的添加时间降序，添加角色级别降序
        searchForm.deleteOrderItem(Role::getCreateTime);
        searchForm.addOrderItem(new OrderItem().orderItem(Role::getLevel, false));
            final SearchResult<Role> searchResult = roleService.getListPage(searchForm);
        return Result.success("查询成功", searchResult);
    }

}
