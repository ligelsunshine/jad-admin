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

package com.jad.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 状态【启用：0,停用：1】
 *
 * @author cxxwl96
 * @since 2021/7/26 21:59
 */
public enum Status {
    ENABLE(0),
    DISABLE(1);

    @EnumValue
    @JsonValue
    private final int index;

    Status(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
