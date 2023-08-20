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

import com.alibaba.fastjson.JSON;
import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.SearchForm;
import com.jad.common.lang.Result;
import com.jad.generator.entity.Generator;
import com.jad.generator.enums.GenerateType;
import com.jad.generator.model.FieldSchema;
import com.jad.generator.model.GenerateConfig;
import com.jad.generator.model.Model;
import com.jad.generator.service.GeneratorService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import cn.hutool.core.io.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代码生成相关接口
 *
 * @author cxxwl96
 * @since 2021/9/11 23:18
 */
@Api(tags = "系统管理 - 代码生成相关接口")
@RestController
@RequestMapping("/devtools/generator")
public class GeneratorController extends BaseController {
    @Autowired
    private GeneratorService generatorService;

    @ApiOperation("添加Model")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:save')")
    public Result<?> save(@RequestBody @Valid Generator generator) {
        final Model model = new Model();
        BeanUtils.copyProperties(generator, model);
        generator.setModel(model);
        if (!generatorService.save(generator)) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("添加Field")
    @PostMapping("/saveField/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:saveField')")
    public Result<?> saveField(@PathVariable String id, @RequestBody @Valid FieldSchema fieldSchema) {
        final Generator generator = generatorService.getById(id);
        if (generator == null) {
            return Result.failed("添加失败，数据不存在");
        }
        final Model model = generator.getModel();
        final List<FieldSchema> fieldSchemas = model.getFieldSchema();
        fieldSchemas.add(fieldSchema);
        model.setFieldSchema(fieldSchemas);
        generator.setModel(model);
        if (!generatorService.updateById(generator)) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除Model")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:delete')")
    public Result<?> delete(@PathVariable String id) {
        if (!generatorService.removeById(id)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("删除Field")
    @DeleteMapping("/deleteField/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:deleteField')")
    public Result<?> deleteField(@PathVariable String id, @RequestParam String fieldId) {
        final Generator generator = generatorService.getById(id);
        if (generator == null) {
            return Result.failed("删除失败，数据不存在");
        }
        final Model model = generator.getModel();
        final List<FieldSchema> fieldSchemas = model.getFieldSchema();
        fieldSchemas.removeIf(fieldSchema -> fieldSchema.getId().equals(fieldId));
        generator.setModel(model);
        if (!generatorService.updateById(generator)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("修改Model")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:update')")
    public Result<?> update(@RequestBody @Valid Generator generator) {
        final Generator entity = generatorService.getById(generator.getId());
        if (entity == null) {
            return Result.failed("数据不存在");
        }
        final Model model = JSON.parseObject(entity.getModelJson(), Model.class);
        BeanUtils.copyProperties(generator, model);
        generator.setModel(model);
        if (!generatorService.updateById(generator)) {
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("修改Field")
    @PutMapping("/updateField/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:updateField')")
    public Result<?> updateField(@PathVariable String id, @RequestBody @Valid FieldSchema fieldSchema) {
        final Generator generator = generatorService.getById(id);
        if (generator == null) {
            return Result.failed("修改失败，数据不存在");
        }
        final Model model = generator.getModel();
        final List<FieldSchema> fieldSchemas = model.getFieldSchema();
        fieldSchemas.forEach(fs -> {
            if (fs.getId().equals(fieldSchema.getId())) {
                BeanUtils.copyProperties(fieldSchema, fs);
            }
        });
        model.setFieldSchema(fieldSchemas);
        generator.setModel(model);
        if (!generatorService.updateById(generator)) {
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("获取所有Field列表")
    @GetMapping("/getFields/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:getFields')")
    public Result<?> getFields(@PathVariable String id) {
        final Generator generator = generatorService.getById(id);
        if (generator == null) {
            return Result.failed("数据不存在");
        }
        final List<FieldSchema> fieldSchemas = generator.getModel().getFieldSchema();
        return Result.success(fieldSchemas);
    }

    @ApiOperation("获取单个Model")
    @GetMapping("/get/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:get')")
    public Result<?> get(@PathVariable String id) {
        return Result.success(generatorService.getById(id));
    }

    @ApiOperation("分页获取Model")
    @PostMapping("/get/page")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:get:page')")
    public Result<?> getPageList(@RequestBody SearchForm searchForm) {
        return Result.success("查询成功", generatorService.getPageList(searchForm));
    }

    @ApiOperation("获取Module")
    @GetMapping("/getModule")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:getModule')")
    public Result<?> getModule() {
        return Result.success(generatorService.getModule());
    }

    @ApiOperation("获取本地Path")
    @GetMapping("/getLocalPath")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:pathSelecct')")
    public Result<?> getLocalPath(String path) {
        return generatorService.getLocalPath(path);
    }

    @ApiOperation("判断是否是路径")
    @GetMapping("/isDirectory")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:pathSelecct')")
    public Result<?> isDirectory(String path) {
        return Result.success(FileUtil.isDirectory(path));
    }

    @ApiOperation("获取上级路径")
    @GetMapping("/getParentPath")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:pathSelecct')")
    public Result<?> getParentPath(String path) {
        String parent = FileUtil.getParent(path, 1);
        if (parent == null) {
            parent = FileUtil.getUserHomePath();
        }
        return Result.success(null, parent);
    }

    @ApiOperation("生成/预览数据库表")
    @PostMapping("/table/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:table')")
    public Result<?> table(@PathVariable String id, @RequestParam GenerateType type) {
        final Generator generator = generatorService.getById(id);
        if (type == GenerateType.VIEW) {
            return generatorService.viewTable(generator.getModel());
        } else if (type == GenerateType.CREATE) {
            generatorService.generateTable(generator.getModel());
        } else {
            return Result.failed("仅支持 预览 或 直接创建");
        }
        return Result.success("生成成功");
    }

    @ApiOperation("生成/预览后端代码")
    @PostMapping("/back/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:back')")
    public Result<?> back(@PathVariable String id, @RequestParam GenerateType type,
        @RequestBody GenerateConfig config) {
        final Generator generator = generatorService.getById(id);
        if (type == GenerateType.VIEW) {
            return generatorService.viewBack(generator.getModel(), config);
        } else if (type == GenerateType.CREATE) {
            return generatorService.generateBack(generator.getModel(), config);
        } else {
            return Result.failed("仅支持 预览 或 直接创建");
        }
    }

    @ApiOperation("生成/预览前端代码")
    @PostMapping("/front/{id}")
    @PreAuthorize("@auth.hasAuthority('devtools:generator:front')")
    public Result<?> front(@PathVariable String id, @RequestParam GenerateType type, @RequestParam String frontPath) {
        final Generator generator = generatorService.getById(id);
        if (type == GenerateType.VIEW) {
            return generatorService.viewFront(generator.getModel(), frontPath);
        } else if (type == GenerateType.CREATE) {
            return generatorService.generateFront(generator.getModel(), frontPath);
        } else {
            return Result.failed("仅支持 预览 或 直接创建");
        }
    }
}
