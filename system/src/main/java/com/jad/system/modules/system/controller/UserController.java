/*
 * Copyright (c) 2021-2021, jad (cxxwl96@sina.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jad.system.modules.system.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.SearchForm;
import com.jad.common.entity.Role;
import com.jad.common.entity.User;
import com.jad.common.enums.UserOrigin;
import com.jad.common.lang.Result;
import com.jad.common.lang.SearchResult;
import com.jad.common.model.dto.UpdatePasswordDto;
import com.jad.common.model.dto.UserBaseInfoDto;
import com.jad.common.service.DeptService;
import com.jad.common.service.RoleService;
import com.jad.common.service.UserService;
import com.jad.system.modules.system.vo.PermCodeVo;
import com.jad.system.modules.system.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    @ApiOperation("添加用户")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('sys:user:save')")
    @Transactional
    public Result<?> save(@RequestBody @Valid User user) {
        User authUser = userService.getCurrentAuthUser();
        String remark = String.format(Locale.ROOT, "来源于'UserController.save 添加用户'接口，添加操作人：%s",
            authUser.getUsername());
        user.setOrigin(UserOrigin.ADMIN_SAVE).setRemark(remark);
        userService.save(user);
        return Result.success("添加成功");
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:user:delete')")
    @Transactional
    public Result<?> delete(@PathVariable String id) {
        userService.removeById(id);
        return Result.success("删除成功");
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('sys:user:update')")
    @Transactional
    public Result<?> update(@RequestBody @Valid User user) {
        userService.update(user);
        return Result.success("修改成功");
    }

    @ApiOperation("修改用户基础信息")
    @PutMapping("/updateBaseInfo")
    @PreAuthorize("@auth.hasAuthority('profile:settings:base')")
    public Result<?> updateBaseInfo(@RequestBody @Valid UserBaseInfoDto dto) {
        userService.updateBaseInfo(dto);
        return Result.success("修改成功");
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/updatePassword")
    @PreAuthorize("@auth.hasAuthority('profile:settings:security')")
    public Result<?> updatePassword(@RequestBody @Valid UpdatePasswordDto dto) {
        dto.validateConfirm();
        boolean result = userService.updatePassword(dto.getOldPassword(), dto.getConfirmPassword());
        return Result.with(result).msgSuccess("修改成功").msgFailed("修改失败").process();
    }

    @ApiOperation("获取单个用户")
    @GetMapping("/get/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:user:get')")
    public Result<?> get(@PathVariable String id) {
        return Result.success(userService.getById(id));
    }

    @ApiOperation("分页获取用户")
    @PostMapping("/get/page")
    @PreAuthorize("@auth.hasAuthority('sys:user:get:page')")
    public Result<?> getPageList(@RequestBody SearchForm searchForm) {
        return Result.success("查询成功", userService.getPageList(searchForm));
    }

    @ApiOperation("获取当前登录的用户信息")
    @GetMapping("/currentUser")
    public Result<?> currentUser() {
        final User user = userService.getCurrentAuthUser();
        final List<Role> roles = userService.getRoles(user.getId());
        user.setRoles(roles);
        user.setRoleIds(roles.stream().map(Role::getId).collect(Collectors.toList()));
        user.setDept(userService.getDept(user.getId()));
        final UserVo userInfo = new UserVo();
        userInfo.setUser(user);
        userInfo.setRoles(roles);
        return Result.success(userInfo);
    }

    @ApiOperation("获取当前登录的权限码")
    @GetMapping("/getPermCode")
    public Result<?> getPermCode() {
        final User user = userService.getCurrentAuthUser();
        final List<String> authorities = userService.getUserAuthority(user.getId());
        PermCodeVo permCodeVo = new PermCodeVo();
        permCodeVo.setSuperRole("ROLE_" + roleService.getAdministrator().getCode());
        permCodeVo.setCodeList(authorities);
        return Result.success(permCodeVo);
    }

    @ApiOperation("搜索用户")
    @GetMapping("/searchUser")
    public Result<?> searchUser(String keyword) {
        // 后期采用ES
        final LambdaQueryChainWrapper<User> wrapper = userService.lambdaQuery()
            .select(User::getId, User::getUsername, User::getName);
        if (!StrUtil.isBlank(keyword)) {
            wrapper.like(User::getUsername, keyword).or().like(User::getName, keyword);
        }
        final SearchResult<User> result = userService.getPageList(1, 20, wrapper.getWrapper());
        return Result.success(result.getItems());
    }
}
