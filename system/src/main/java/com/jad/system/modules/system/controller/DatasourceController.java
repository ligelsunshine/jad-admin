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
import com.jad.common.entity.Datasource;
import com.jad.common.lang.IDs;
import com.jad.common.lang.Result;
import com.jad.common.service.DatasourceService;

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
 * 数据源相关接口
 *
 * @author cxxwl96
 * @since 2021/10/17 00:13
 */
@Api(tags = "系统管理 - 数据源相关接口")
@RestController
@RequestMapping("/sys/datasource")
public class DatasourceController extends BaseController {
    @Autowired
    private DatasourceService datasourceService;

    @ApiOperation("添加数据源")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('sys:datasource:save')")
    public Result<?> save(@RequestBody @Valid Datasource datasource) {
        if (!datasourceService.save(datasource)) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除数据源")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:datasource:delete')")
    public Result<?> delete(@PathVariable String id) {
        if (!datasourceService.removeById(id)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("删除多个数据源")
    @DeleteMapping("/deleteArr")
    @PreAuthorize("@auth.hasAuthority('sys:datasource:deleteArr')")
    public Result<?> deleteArr(@RequestBody @Valid IDs ids) {
        if (!datasourceService.removeByIds(ids.getIds())) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("修改数据源")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('sys:datasource:update')")
    public Result<?> update(@RequestBody @Valid Datasource datasource) {
        if (!datasourceService.updateById(datasource)) {
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("获取单个数据源")
    @GetMapping("/get/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:datasource:get')")
    public Result<?> get(@PathVariable String id) {
        final Datasource datasource = datasourceService.getById(id);
        if (datasource == null) {
            return Result.failed("获取数据失败");
        }
        return Result.success(datasource);
    }

    @ApiOperation("获取所有数据源")
    @GetMapping("/getAll")
    @PreAuthorize("@auth.hasAuthority('sys:datasource:getAll')")
    public Result<?> getAll() {
        final List<Datasource> list = datasourceService.list();
        if (list == null) {
            return Result.failed("获取数据失败");
        }
        return Result.success(list);
    }

    @ApiOperation("分页获取数据源")
    @PostMapping("/get/page")
    @PreAuthorize("@auth.hasAuthority('sys:datasource:get:page')")
    public Result<?> getPageList(@RequestBody SearchForm searchForm) {
        return Result.success("查询成功", datasourceService.getPageList(searchForm));
    }
}