/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.system.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.entity.Role;
import com.jad.common.lang.Result;
import com.jad.common.service.RoleService;
import com.jad.system.modules.system.dto.RoleDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-29
 */
@Api(tags = "系统管理 - 角色相关接口")
@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("添加系统角色")
    @PostMapping("/save")
    public Result save(@RequestBody RoleDto roleDto) {
        final Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        final boolean success = roleService.save(role);
        if (success) {
            return Result.success("添加成功");
        }
        return Result.failed("添加失败");
    }

    @ApiOperation("删除系统角色")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        final boolean success = roleService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.failed("删除失败");
    }

    @ApiOperation("获取系统角色")
    @GetMapping("/list")
    public Result list() {
        final List<Role> list = roleService.list();
        return Result.success("查询成功", list);
    }

}
