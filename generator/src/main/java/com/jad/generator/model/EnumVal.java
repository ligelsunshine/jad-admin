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

import com.jad.common.utils.NamingUtil;

import lombok.Data;

/**
 * 枚举类型的枚举值
 *
 * @author cxxwl96
 * @since 2021/9/12 23:04
 */
@Data
public class EnumVal {
    // 中文名
    private String title;

    // 枚举对象名
    private String name;

    public String getBigHump() {
        return NamingUtil.toBigHump(name);
    }

    public String getSmallHump() {
        return NamingUtil.toSmallHump(name);
    }

    public String getLowerCaseUnderline() {
        return NamingUtil.toLowerCaseUnderline(name);
    }

    public String getUpperCaseUnderline() {
        return NamingUtil.toUpperCaseUnderline(name);
    }
}
