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

import io.swagger.annotations.ApiModel;

/**
 * 条件
 *
 * @author cxxwl96
 * @since 2021/8/22 16:55
 */
@ApiModel(value = "条件")
public enum Condition {
    EQ("="),
    NE("!="),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    LIKE("like"),
    RANGE_TIME("range time");

    private final String condition;

    Condition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
