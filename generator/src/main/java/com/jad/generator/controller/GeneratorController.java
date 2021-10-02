/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.controller;

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
import com.jad.generator.service.impl.GeneratorServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;

import javax.validation.Valid;

import cn.hutool.core.io.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代码生成 - 代码生成相关接口
 *
 * @author cxxwl96
 * @since 2021/9/11 23:18
 */
@Api(tags = "代码生成 - 代码生成相关接口")
@RestController
@RequestMapping("/devtools/generator")
public class GeneratorController extends BaseController {
    @Autowired
    private GeneratorService generatorService;

    @ApiOperation("添加Model")
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Generator generator) {
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
    public Result saveField(@PathVariable String id, @RequestBody @Valid FieldSchema fieldSchema) {
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
    public Result delete(@PathVariable String id) {
        if (!generatorService.removeById(id)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("删除Field")
    @DeleteMapping("/deleteField/{id}")
    public Result deleteField(@PathVariable String id, @RequestParam String fieldId) {
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
    public Result update(@RequestBody @Valid Generator generator) {
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
    public Result updateField(@PathVariable String id, @RequestBody @Valid FieldSchema fieldSchema) {
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
    public Result getFields(@PathVariable String id) {
        final Generator generator = generatorService.getById(id);
        if (generator == null) {
            return Result.failed("数据不存在");
        }
        final List<FieldSchema> fieldSchemas = generator.getModel().getFieldSchema();
        return Result.success(fieldSchemas);
    }

    @ApiOperation("获取单个代码生成")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        return Result.success(generatorService.getById(id));
    }

    @ApiOperation("分页获取代码生成")
    @PostMapping("/get/page")
    public Result getPageList(@RequestBody SearchForm searchForm) {
        return Result.success("查询成功", generatorService.getPageList(searchForm));
    }

    @ApiOperation("获取Module")
    @GetMapping("/getModule")
    public Result getModule() {
        return Result.success(generatorService.getModule());
    }

    @ApiOperation("生成数据库表")
    @PostMapping("/table/{id}")
    public Result table(@PathVariable String id, @RequestParam GenerateType type) {
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

    @ApiOperation("生成后端代码")
    @PostMapping("/back/{id}")
    public Result back(@PathVariable String id, @RequestParam GenerateType type, GenerateConfig config) {
        final Generator generator = generatorService.getById(id);
        if (type == GenerateType.VIEW) {
            return generatorService.viewBack(generator.getModel(), config);
        } else if (type == GenerateType.CREATE) {
            generatorService.generateBack(generator.getModel(), config);
        }
        return Result.success("生成成功");
    }

    @ApiOperation("生成前端代码")
    @PostMapping("/front/{id}")
    public Result front(@PathVariable String id, @RequestParam GenerateType type) {
        final Generator generator = generatorService.getById(id);
        generatorService.generateFront(generator.getModel());
        return Result.success();
    }

    public static void main(String[] args) {
        final String path = Objects.requireNonNull(GeneratorController.class.getResource("/templates/model.json"))
            .getPath();
        final String jsonStr = FileUtil.readUtf8String(path);
        final Model model = JSON.parseObject(jsonStr, Model.class);

        final GeneratorServiceImpl service = new GeneratorServiceImpl();
        service.generateTable(model);
        // service.generateBack(model);
    }
}
