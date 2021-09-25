/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.service;

import com.jad.common.base.service.BaseService;
import com.jad.generator.entity.Generator;
import com.jad.generator.model.Model;
import com.jad.generator.model.Module;

import java.util.List;

/**
 * GeneratorService
 *
 * @author cxxwl96
 * @since 2021/9/11 23:16
 */
public interface GeneratorService extends BaseService<Generator> {
    /**
     * 获取module
     *
     * @return module
     */
    List<Module> getModule();

    /**
     * 生成数据库表DDL
     *
     * @param model model
     */
    void generateTable(Model model);

    /**
     * 生成后端代码
     *
     * @param model model
     */
    void generateBack(Model model);

    /**
     * 生成前端代码
     *
     * @param model model
     */
    void generateFront(Model model);
}
