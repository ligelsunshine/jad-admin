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

import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.SearchForm;
import com.jad.common.entity.DictData;
import com.jad.common.lang.Result;
import com.jad.common.service.DictDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 字典数据相关接口
 *
 * @author cxxwl96
 * @since 2021/11/05 21:07
 */
@Api(tags = "系统管理 - 字典数据相关接口")
@RestController
@RequestMapping("/sys/dictData")
public class DictDataController extends BaseController {
    @Autowired
    private DictDataService dictDataService;

    @ApiOperation("添加字典数据")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('sys:dictData:save')")
    public Result save(@RequestBody @Valid DictData dictData) {
        if (!dictDataService.save(dictData)) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除字典数据")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:dictData:delete')")
    public Result delete(@PathVariable String id) {
        if (!dictDataService.removeById(id)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("修改字典数据")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('sys:dictData:update')")
    public Result update(@RequestBody @Valid DictData dictData) {
        if (!dictDataService.updateById(dictData)) {
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("分页获取字典数据")
    @PostMapping("/get/page")
    @PreAuthorize("@auth.hasAuthority('sys:dictData:get:page')")
    public Result getPageList(@RequestBody SearchForm searchForm) {
        return Result.success("查询成功", dictDataService.getPageList(searchForm));
    }
}