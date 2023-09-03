/*
 * Copyright (c) 2021-2023, jad (cxxwl96@sina.com).
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

import com.jad.common.base.controller.BaseController;
import com.jad.common.entity.Settings;
import com.jad.common.lang.Result;
import com.jad.common.service.SettingsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * 系统设置管理相关接口
 *
 * @author cxxwl96
 * @since 2023/08/28 22:24
 */
@Api(tags = "系统设置管理相关接口")
@RestController
@RequestMapping("/sys/settingsMgr")
public class SettingsMgrController extends BaseController {
    @Autowired
    private SettingsService settingsService;

    @ApiOperation("添加系统设置管理")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('sys:settings:save')")
    public Result<?> save(@RequestBody @Valid Settings settings) {
        if (!settingsService.save(settings)) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除系统设置管理")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:settings:delete')")
    public Result<?> delete(@PathVariable String id, boolean includeSelf) {
        if (!settingsService.removeTree(id, includeSelf)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("修改系统设置管理")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('sys:settings:update')")
    public Result<?> update(@RequestBody @Valid Settings settings) {
        if (!settingsService.updateById(settings)) {
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("获取单个系统设置管理")
    @GetMapping("/get/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:settings:get')")
    public Result<?> get(@PathVariable String id) {
        final Settings settings = settingsService.getById(id);
        if (settings == null) {
            return Result.failed("获取数据失败");
        }
        return Result.success(settings);
    }

    @ApiOperation("获取系统设置管理树")
    @GetMapping("/getTree")
    @PreAuthorize("@auth.hasAuthority('sys:settings:getTree')")
    public Result<?> getTree() {
        List<Settings> treeList = settingsService.getRootTree();
        return Result.success(treeList);
    }

    @ApiOperation("设置绑定菜单权限")
    @PostMapping("/bindMenu/{menuId}")
    @PreAuthorize("@auth.hasAuthority('sys:settings:bindMenu')")
    public Result<?> bindMenu(@PathVariable String menuId) {
        settingsService.bindMenu(menuId);
        return Result.success("绑定成功");
    }
}