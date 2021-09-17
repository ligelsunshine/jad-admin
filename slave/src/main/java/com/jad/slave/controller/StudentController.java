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

package com.jad.slave.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.SearchForm;
import com.jad.common.lang.Result;
import com.jad.slave.entity.Student;
import com.jad.slave.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
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
 * 学生相关接口
 *
 * @author cxxwl96
 * @since 2021/09/16 23:36
 */
@Api(tags = "学生相关接口")
@RestController
@RequestMapping("/slave/student")
public class StudentController extends BaseController {
    @Autowired
    private StudentService studentService;

    @ApiOperation("添加学生")
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Student student) {
        if (!studentService.save(student)) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除学生")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        if (!studentService.removeById(id)) {
            return Result.success("删除失败");
        }
        return Result.failed("删除成功");
    }

    @ApiOperation("修改学生")
    @PutMapping("/update")
    public Result update(@RequestBody @Valid Student student) {
        if (!studentService.updateById(student)) {
            return Result.success("修改失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("获取单个学生")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        return Result.success(studentService.getById(id));
    }

    @ApiOperation("分页获取学生")
    @PostMapping("/get/page")
    public Result getPageList(@RequestBody SearchForm searchForm) {
        return Result.success("查询成功", studentService.getPageList(searchForm));
    }
}