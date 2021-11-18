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
import com.jad.common.entity.DataClassify;
import com.jad.common.lang.Result;
import com.jad.common.service.DataClassifyService;

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

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据分类相关接口
 *
 * @author cxxwl96
 * @since 2021/11/12 23:21
 */
@Api(tags = "系统管理 - 数据分类相关接口")
@RestController
@RequestMapping("/sys/dataClassify")
public class DataClassifyController extends BaseController {
    @Autowired
    private DataClassifyService dataClassifyService;

    @ApiOperation("添加数据分类")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('sys:dataClassify:save')")
    public Result save(@RequestBody @Valid DataClassify dataClassify) {
        if (!dataClassifyService.save(dataClassify)) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除数据分类")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:dataClassify:delete')")
    public Result delete(@PathVariable String id, boolean includeSelf) {
        if (!dataClassifyService.removeTree(id, includeSelf)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("修改数据分类")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('sys:dataClassify:update')")
    public Result update(@RequestBody @Valid DataClassify dataClassify) {
        if (!dataClassifyService.updateById(dataClassify)) {
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("获取单个数据分类")
    @GetMapping("/get/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:dataClassify:get')")
    public Result get(@PathVariable String id) {
        final DataClassify dataClassify = dataClassifyService.getById(id);
        if (dataClassify == null) {
            return Result.failed("获取数据失败");
        }
        return Result.success(dataClassify);
    }

    @ApiOperation("获取数据分类树")
    @GetMapping("/getTree")
    @PreAuthorize("@auth.hasAuthority('sys:dataClassify:getTree')")
    public Result getTree(String code, boolean includeSelf) {
        return dataClassifyService.getTree(code, includeSelf);
    }
}