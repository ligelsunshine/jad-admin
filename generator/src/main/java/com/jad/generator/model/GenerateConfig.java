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

package com.jad.generator.model;

import lombok.Data;

/**
 * 生成配置
 *
 * @author cxxwl96
 * @since 2021/9/13 00:43
 */
@Data
public class GenerateConfig {
    // 是否生成entity
    private boolean entity = true;

    // 是否生成mapper
    private boolean mapper = true;

    // 是否生成mapperXml
    private boolean mapperXml = true;

    // 是否生成service
    private boolean service = true;

    // 是否生成serciceImpl
    private boolean serviceImpl = true;

    // 是否生成controller
    private boolean controller = true;
}
