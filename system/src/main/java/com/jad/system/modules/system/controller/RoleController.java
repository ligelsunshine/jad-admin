/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.system.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.SearchForm;
import com.jad.common.entity.Role;
import com.jad.common.entity.RoleMenu;
import com.jad.common.enums.Status;
import com.jad.common.exception.BadRequestException;
import com.jad.common.function.PropertyFunc;
import com.jad.common.lang.Result;
import com.jad.common.lang.SearchResult;
import com.jad.common.service.MenuService;
import com.jad.common.service.RoleMenuService;
import com.jad.common.service.RoleService;
import com.jad.system.modules.system.dto.AssignPermissionsDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import cn.hutool.core.collection.CollUtil;
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

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

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
    @PutMapping("/update")
    public Result update(@RequestBody @Valid Role role) {
        if (roleService.updateById(role)) {
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
        return Result.success(roleService.getList());
    }

    @ApiOperation("分页获取系统角色")
    @PostMapping("/get/page")
    public Result getPageList(@RequestBody SearchForm searchForm) {
        final SearchResult<Role> searchResult = roleService.getPageList(searchForm);
        return Result.success("查询成功", searchResult);
    }

    @ApiOperation("修改状态")
    @PutMapping("/update/status")
    public Result updateStatus(@RequestParam String id, @RequestParam String status) {
        if (roleService.updateStatus(id, Status.valueOf(status))) {
            return Result.success("修改成功");
        }
        return Result.failed("修改失败");
    }

    @ApiOperation("获取角色菜单ID")
    @GetMapping("/getRoleMenuIds")
    public Result getRoleMenuIds(@RequestParam String roleId) {
        final List<String> halfChecked = getRoleMenuIds(roleId, true);
        final List<String> checked = getRoleMenuIds(roleId, false);
        final HashMap<String, List<String>> result = new HashMap<>();
        result.put("halfChecked", halfChecked);
        result.put("checked", checked);
        return Result.success(result);
    }

    @ApiOperation("分配权限")
    @PutMapping("/assignPermissions")
    @Transactional
    public Result assignPermissions(@RequestBody @Valid AssignPermissionsDto dto) {
        if (CollUtil.isEmpty(dto.getRoleMenus())) {
            return Result.failed("最少必须分配一个菜单");
        }
        // 先全部删除
        int count = roleMenuService.lambdaQuery().eq(RoleMenu::getRoleId, dto.getRoleId()).count();
        if (count > 0) {
            final Map<String, Object> columnMap = new HashMap<>();
            PropertyFunc<RoleMenu, ?> column = RoleMenu::getRoleId;
            columnMap.put(column.getColumnName(), dto.getRoleId());
            if (!roleMenuService.removeByMap(columnMap)) {
                throw new BadRequestException("操作失败");
            }
        }

        // 再添加
        final List<RoleMenu> roleMenus = new ArrayList<>();
        dto.getRoleMenus().forEach(item -> {
            final RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(dto.getRoleId());
            roleMenu.setMenuId(item.getMenuId());
            roleMenu.setHalfChecked(item.isHalfChecked());
            roleMenus.add(roleMenu);
        });
        if (!roleMenuService.saveBatch(roleMenus)) {
            throw new BadRequestException("操作失败");
        }
        return Result.success("操作成功");
    }

    private List<String> getRoleMenuIds(String roleId, boolean isHalfChecked) {
        return roleMenuService.lambdaQuery()
            .eq(RoleMenu::getRoleId, roleId)
            .eq(RoleMenu::isHalfChecked, isHalfChecked)
            .list()
            .stream()
            .map(RoleMenu::getMenuId)
            .collect(Collectors.toList());
    }
}
