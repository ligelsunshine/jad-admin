/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.controller;

import com.alibaba.fastjson.JSON;
import com.jad.common.base.controller.BaseController;
import com.jad.common.lang.Result;
import com.jad.generator.model.Model;
import com.jad.generator.service.GeneratorService;
import com.jad.generator.service.impl.GeneratorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
@RequestMapping("/sys/codeGenerate")
public class GeneratorController extends BaseController {

    @Autowired
    private GeneratorService service;

    @ApiOperation("生成后端代码")
    @PostMapping("/back")
    public Result back() {

        return Result.success();
    }

    @ApiOperation("生成前端代码")
    @PostMapping("/front")
    public Result front() {

        return Result.success();
    }

    public static void main(String[] args) {
        final String path = Objects.requireNonNull(
            GeneratorController.class.getResource("/templates/back/entity.data.json")).getPath();
        final String jsonStr = FileUtil.readUtf8String(path);
        final Model model = JSON.parseObject(jsonStr, Model.class);

        final GeneratorServiceImpl service = new GeneratorServiceImpl();
        service.generateBack(model);
    }
}
