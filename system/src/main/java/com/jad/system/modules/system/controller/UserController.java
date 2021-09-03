/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.system.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.SearchForm;
import com.jad.common.entity.Dept;
import com.jad.common.entity.Role;
import com.jad.common.entity.User;
import com.jad.common.entity.UserRole;
import com.jad.common.exception.BadRequestException;
import com.jad.common.function.PropertyFunc;
import com.jad.common.lang.Result;
import com.jad.common.lang.SearchResult;
import com.jad.common.service.DeptService;
import com.jad.common.service.RoleService;
import com.jad.common.service.UserRoleService;
import com.jad.common.service.UserService;
import com.jad.system.modules.system.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统用户相关接口
 *
 * @author cxxwl96
 * @since 2021/6/29 00:56
 */
@Api(tags = "系统管理 - 用户相关接口")
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ApiOperation("添加用户")
    @PostMapping("/save")
    @Transactional
    public Result save(@RequestBody @Valid User user) {
        // 校验k
        validate(user, false);
        // 添加用户
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!userService.save(user)) {
            throw new BadRequestException("添加用户失败");
        }
        // 分配角色
        List<UserRole> userRoles = new ArrayList<>();
        user.getRoleIds().forEach(roleId -> {
            final UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        });
        if (!userRoleService.saveBatch(userRoles)) {
            throw new BadRequestException("分配角色失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id}")
    @Transactional
    public Result delete(@PathVariable String id) {
        PropertyFunc<UserRole, ?> userIdColumn = UserRole::getUserId;
        final Map<String, Object> map = new HashMap<>();
        map.put(userIdColumn.getColumnName(), id);
        if (!userRoleService.removeByMap(map)) {
            throw new BadRequestException("删除用户角色失败");
        }
        if (!userService.removeById(id)) {
            throw new BadRequestException("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    @Transactional
    public Result update(@RequestBody @Valid User user) {
        // 校验
        validate(user, true);
        // 密码加密
        if (StrUtil.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (!userService.updateById(user)) {
            throw new BadRequestException("修改用户失败");
        }
        // 分配角色
        // 删除原来分配的角色
        PropertyFunc<UserRole, ?> userIdColumn = UserRole::getUserId;
        final Map<String, Object> map = new HashMap<>();
        map.put(userIdColumn.getColumnName(), user.getId());
        if (!userRoleService.removeByMap(map)) {
            throw new BadRequestException("分配角色失败");
        }
        // 添加新分配的角色
        List<UserRole> userRoles = new ArrayList<>();
        user.getRoleIds().forEach(roleId -> {
            final UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        });
        if (!userRoleService.saveBatch(userRoles)) {
            throw new BadRequestException("分配角色失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("获取单个用户")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        return Result.success(userService.getById(id));
    }

    @ApiOperation("获取所有用户")
    @GetMapping("/get/list")
    public Result getList() {
        return Result.success(userService.list());
    }

    @ApiOperation("分页获取用户")
    @PostMapping("/get/page")
    public Result getPageList(@RequestBody SearchForm searchForm) {
        final SearchResult<User> searchResult = userService.getPageList(searchForm);
        final List<User> users = searchResult.getItems();
        users.forEach(user -> {
            // 初始化角色信息
            final List<String> roleIds = userRoleService.lambdaQuery()
                .eq(UserRole::getUserId, user.getId())
                .list()
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
            if (roleIds.size() > 0) {
                final List<Role> roles = roleService.lambdaQuery().in(Role::getId, roleIds).list();
                user.setRoleIds(roleIds);
                user.setRoles(roles);
            }
            // 初始化部门信息
            if (deptService.exist(user.getDeptId())) {
                final Dept dept = deptService.getById(user.getDeptId());
                user.setDept(dept);
            }
            user.setPassword(null);
        });
        return Result.success("查询成功", searchResult);
    }

    @ApiOperation("获取当前登录的用户信息")
    @GetMapping("/currentUser")
    public Result currentUser() {
        final User user = userService.getCurrentAuthUser();
        final List<Role> roles = userService.getRoles(user.getId());
        final UserVo userInfo = new UserVo();
        userInfo.setUser(user);
        userInfo.setRoles(roles);

        return Result.success(userInfo);
    }

    @ApiOperation("获取当前登录的权限码")
    @GetMapping("/getPermCode")
    public Result getPermCode() {
        final User user = userService.getCurrentAuthUser();
        final String userAuthority = userService.getUserAuthority(user.getId());
        final String[] permCode = userAuthority.split(",");
        return Result.success(permCode);
    }

    /**
     * 校验用户
     *
     * @param user 用户
     * @param isUpdate 是否是更新操作
     */
    private void validate(User user, boolean isUpdate) {
        int count;
        // 校验账号是否唯一
        if (isUpdate) {
            count = userService.lambdaQuery()
                .eq(User::getUsername, user.getUsername())
                .ne(User::getId, user.getId())
                .count();
        } else {
            count = userService.lambdaQuery().eq(User::getUsername, user.getUsername()).count();
        }
        if (count > 0) {
            throw new BadRequestException("该用户已存在");
        }
    }
}
