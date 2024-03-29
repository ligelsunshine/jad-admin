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

package com.jad.generator.service;

import com.jad.common.base.service.BaseService;
import com.jad.common.lang.Result;
import com.jad.generator.entity.Generator;
import com.jad.generator.model.GenerateConfig;
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
     * 获取本地路径
     *
     * @param path path
     * @return 本地路径
     */
    Result<?> getLocalPath(String path);

    /**
     * 生成数据库表DDL
     *
     * @param model model
     */
    void generateTable(Model model);

    /**
     * 预览数据库表DDL
     *
     * @param model model
     * @return 预览结果
     */
    Result<?> viewTable(Model model);

    /**
     * 生成后端代码
     *
     * @param model model
     * @return 生成结果
     */
    Result<?> generateBack(Model model, GenerateConfig config);

    /**
     * 预览后端代码
     *
     * @param model model
     * @param config config
     * @return 预览结果
     */
    Result<?> viewBack(Model model, GenerateConfig config);

    /**
     * 生成前端代码
     *
     * @param model model
     * @param frontPath frontPath
     * @return 生成结果
     */
    Result<?> generateFront(Model model, String frontPath);

    /**
     * 预览前端代码
     *
     * @param model model
     * @param frontPath frontPath
     * @return 预览结果
     */
    Result<?> viewFront(Model model, String frontPath);
}
