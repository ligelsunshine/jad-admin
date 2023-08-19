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

package com.jad.filestore.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.SearchForm;
import com.jad.common.lang.IDs;
import com.jad.common.lang.Result;
import com.jad.filestore.entity.FileStore;
import com.jad.filestore.service.FileStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 对象存储相关接口
 *
 * @author cxxwl96
 * @since 2021/11/20 21:12
 */
@Api(tags = "系统管理 - 对象存储相关接口")
@RestController
@RequestMapping("/sys/fileStore")
public class FileStoreController extends BaseController {
    @Autowired
    private FileStoreService fileStoreService;

    @ApiOperation("添加对象存储")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('sys:fileStore:save')")
    public Result<?> save(@RequestBody @Valid FileStore fileStore) {
        if (!fileStoreService.save(fileStore)) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除对象存储")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:fileStore:delete')")
    public Result<?> delete(@PathVariable String id) {
        if (!fileStoreService.delete(id)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("删除多个对象存储")
    @DeleteMapping("/deleteArr")
    @PreAuthorize("@auth.hasAuthority('sys:fileStore:deleteArr')")
    public Result<?> deleteArr(@RequestBody @Valid IDs ids) {
        if (!fileStoreService.deleteArr(ids.getIds())) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("获取单个对象存储")
    @GetMapping("/get/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:fileStore:get')")
    public Result<?> get(@PathVariable String id) {
        final FileStore fileStore = fileStoreService.getById(id);
        if (fileStore == null) {
            return Result.failed("获取数据失败");
        }
        return Result.success(fileStore);
    }

    @ApiOperation("分页获取对象存储")
    @PostMapping("/get/page")
    @PreAuthorize("@auth.hasAuthority('sys:fileStore:get:page')")
    public Result<?> getPageList(@RequestBody SearchForm searchForm) {
        return Result.success("查询成功", fileStoreService.getPageList(searchForm));
    }

    @ApiOperation("分组获取对象存储")
    @GetMapping("/getList/{groupId}")
    @PreAuthorize("@auth.hasAuthority('sys:fileStore:get:list:groupId')")
    public Result<?> getListByGroup(@PathVariable String groupId) {
        return Result.success("查询成功", fileStoreService.getFileStoreByGroup(groupId));
    }
}