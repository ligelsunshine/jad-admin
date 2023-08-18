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
import com.jad.common.service.RoleMenuService;
import com.jad.common.service.RoleService;
import com.jad.common.service.UserService;
import com.jad.system.modules.system.dto.AssignPermissionsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @ApiOperation("添加系统角色")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('sys:role:save')")
    public Result<?> save(@RequestBody @Valid Role role) {
        if (roleService.save(role)) {
            return Result.success("添加成功");
        }
        return Result.failed("添加失败");
    }

    @ApiOperation("删除系统角色")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:role:delete')")
    public Result<?> delete(@PathVariable String id) {
        if (roleService.removeById(id)) {
            return Result.success("删除成功");
        }
        return Result.failed("删除失败");
    }

    @ApiOperation("修改系统角色")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('sys:role:update')")
    public Result<?> update(@RequestBody @Valid Role role) {
        if (roleService.updateById(role)) {
            return Result.success("修改成功");
        }
        return Result.failed("修改失败");
    }

    @ApiOperation("修改默认角色")
    @PutMapping("/update/defaultRole")
    @PreAuthorize("@auth.hasAuthority('sys:role:update:defaultRole')")
    public Result<?> updateDefaultRole(@RequestParam String id) {
        if (roleService.updateDefaultRole(id)) {
            return Result.success("已成功修改默认角色");
        }
        return Result.failed("修改默认角色失败");
    }

    @ApiOperation("获取默认角色")
    @GetMapping("/get/defaultRole")
    public Result<?> getDefaultRole() {
        return Result.success(roleService.getDefaultRole());
    }

    @ApiOperation("修改状态")
    @PutMapping("/update/status")
    @PreAuthorize("@auth.hasAuthority('sys:role:update:status')")
    public Result<?> updateStatus(@RequestParam String id, @RequestParam String status) {
        if (roleService.updateStatus(id, Status.valueOf(status))) {
            return Result.success("已成功修改角色状态");
        }
        return Result.failed("修改角色状态失败");
    }

    @ApiOperation("获取所有系统角色")
    @GetMapping("/get/list")
    @PreAuthorize("@auth.hasAuthority('sys:role:get:list')")
    public Result<?> getList() {
        return Result.success(roleService.getList());
    }

    @ApiOperation("分页获取系统角色")
    @PostMapping("/get/page")
    @PreAuthorize("@auth.hasAuthority('sys:get:page')")
    public Result<?> getPageList(@RequestBody SearchForm searchForm) {
        final SearchResult<Role> searchResult = roleService.getPageList(searchForm);
        return Result.success("查询成功", searchResult);
    }

    @ApiOperation("获取角色菜单ID")
    @GetMapping("/getRoleMenuItems")
    @PreAuthorize("@auth.hasAuthority('sys:role:assignPermissions')")
    public Result<?> getRoleMenuItems(@RequestParam String roleId) {
        final List<RoleMenu> roleMenus = roleMenuService.lambdaQuery().eq(RoleMenu::getRoleId, roleId).list();
        List<AssignPermissionsDto.MenuItem> menuItems = new ArrayList<>();
        roleMenus.forEach(roleMenu -> {
            final AssignPermissionsDto.MenuItem menuItem = new AssignPermissionsDto.MenuItem();
            menuItem.setMenuId(roleMenu.getMenuId());
            menuItem.setLeaf(roleMenu.isLeaf());
            menuItems.add(menuItem);
        });
        return Result.success(menuItems);
    }

    @ApiOperation("分配权限")
    @PutMapping("/assignPermissions")
    @Transactional
    @PreAuthorize("@auth.hasAuthority('sys:role:assignPermissions')")
    public Result<?> assignPermissions(@RequestBody @Valid AssignPermissionsDto dto) {
        // 先全部删除
        long count = roleMenuService.lambdaQuery().eq(RoleMenu::getRoleId, dto.getRoleId()).count();
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
        dto.getMenuItems().forEach(item -> {
            final RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(dto.getRoleId());
            roleMenu.setMenuId(item.getMenuId());
            roleMenu.setLeaf(item.isLeaf());
            roleMenus.add(roleMenu);
        });
        if (!roleMenuService.saveBatch(roleMenus)) {
            throw new BadRequestException("操作失败");
        }
        userService.clearUserAuthorityByRoleId(dto.getRoleId());
        return Result.success("操作成功");
    }
}
