/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.model;

import com.jad.common.utils.NamingUtil;
import com.jad.generator.enums.ComponentType;
import com.jad.generator.enums.FieldType;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 字段约束
 *
 * @author cxxwl96
 * @since 2021/9/12 14:12
 */
@Data
public class FieldSchema {
    // 字段标题
    @NotBlank(message = "字段标题不能为空")
    private String title;

    // 字段名
    @NotBlank(message = "字段名不能为空")
    private String name;

    // 字段类型
    @NotNull(message = "字段类型不能为空")
    private FieldType type;

    // 类型
    @NotNull(message = "组件不能为空")
    private ComponentType component;

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
