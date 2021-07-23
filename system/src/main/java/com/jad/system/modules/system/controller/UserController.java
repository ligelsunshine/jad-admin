/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.system.modules.system.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.entity.Role;
import com.jad.common.entity.User;
import com.jad.common.lang.Result;
import com.jad.common.service.UserService;
import com.jad.system.modules.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
