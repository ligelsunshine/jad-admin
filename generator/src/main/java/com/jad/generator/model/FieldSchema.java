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
import com.jad.generator.enums.ComponentType;
import com.jad.generator.enums.FieldType;
import com.jad.generator.enums.Present;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import cn.hutool.core.lang.UUID;
import lombok.Data;

/**
 * 字段约束
 *
 * @author cxxwl96
 * @since 2021/9/12 14:12
 */
@Data
public class FieldSchema {

    private String id = UUID.fastUUID().toString(true);

    // 字段标题
    @NotBlank(message = "字段标题不能为空")
    private String title;

    // 字段名
    @NotBlank(message = "字段名不能为空")
    private String name;

    // 字段类型
    private FieldType type = FieldType.STRING;

    // 组件
    private ComponentType component = ComponentType.Input;

    // 默认值
    private Object defaultVal;

    // 枚举类型的枚举值
    private List<EnumVal> enumVal;

    // 是否必须
    private boolean require;

    // 字段排序
    private int orderNo = 0;

    // 自定义规则
    private List<Rule> rules;

    // 控制字段出现的位置：列表展示、添加表单、编辑表单、查询表单
    private Set<Present> presents = new HashSet<>();

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

    public void addPresent(Present present) {
        this.presents.add(present);
    }

    public void removePresent(Present present) {
        this.presents.remove(present);
    }

}
