/*
 * Copyright (c) 2021-2023, jad (cxxwl96@sina.com).
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

package com.jad.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 设置类型
 * - 目录:directory
 * - 设置页:page
 * - 设置项:item
 *
 * @author cxxwl96
 * @since 2023/08/28 22:24
 */
public enum SettingType {
    DIRECTORY(0),
    PAGE(1),
    ITEM(2);

    @EnumValue
    @JsonValue
    private final int index;

    SettingType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
