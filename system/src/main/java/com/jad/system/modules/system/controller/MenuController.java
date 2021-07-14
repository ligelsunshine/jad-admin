/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.system.modules.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jad.common.base.controller.BaseController;
import com.jad.common.entity.Menu;
import com.jad.common.lang.Result;
import com.jad.common.service.MenuService;
import com.jad.system.modules.system.dto.MenuDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-29
 */
@Api(tags = "系统管理 - 菜单权限相关接口")
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("添加菜单权限")
    @PostMapping("/save")
    public Result save(@RequestBody MenuDto menuDto) {
        final Menu menu = new Menu();
        BeanUtil.copyProperties(menuDto, menu);
        final boolean success = menuService.save(menu);
        if (success) {
            return Result.success("添加成功");
        }
        return Result.failed("添加失败");
    }
}
