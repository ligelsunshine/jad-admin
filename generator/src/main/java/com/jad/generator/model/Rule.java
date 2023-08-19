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

import com.jad.generator.enums.RuleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 自定义规则
 * 拥有以下几种类型
 * - {type: "STRING_LEN", len: 6}
 * - {type: "STRING_RANGE", min: 1, max: 6}
 * - {type: "除STRING_LEN、STRING_RANGE、REGEXP以外的其他类型"}
 * - {type: "REGEXP", message: "错误消息", pattern: "正则表达式"}
 *
 * @author cxxwl96
 * @since 2021/9/12 14:29
 */
@Data
public class Rule {
    // 规则类型
    @NotNull(message = "请选择规则类型")
    private RuleType type;

    // 定长，当且仅当type==STRING时，与max,min互斥
    private Integer len;

    // 最小值，当且仅当type==STRING时，与max一起使用，与len互斥
    private Integer min;

    // 最大值，当且仅当type==STRING时，与min一起使用，与len互斥
    private Integer max;

    // 错误消息，与pattern一起使用
    @NotBlank(message = "错误消息不能为空")
    private String message;

    // 正则，自定义规则，与message一起使用
    private String pattern;
}
