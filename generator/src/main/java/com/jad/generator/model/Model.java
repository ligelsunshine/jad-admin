/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.model;

import com.jad.common.utils.NamingUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

/**
 * Model
 *
 * @author cxxwl96
 * @since 2021/9/12 14:11
 */
@Data
public class Model {

    @NotBlank(message = "请选择module")
    private String module;

    // model标题
    @NotBlank(message = "Model标题不能为空")
    private String title;

    // model名
    @NotBlank(message = "Model名不能为空")
    private String name;

    private String namespace;

    // 主属性字段
    @NotBlank(message = "主属性不能为空")
    private String mainField;

    // 是否属于树形结构model
    private boolean treeModel;

    // 是否逻辑删除
    private boolean logic;

    // 字段约束
    private List<FieldSchema> fieldSchema;

    public String getBigHump() {
        return NamingUtil.toBigHump(name);
    }

    public String getSmallHump() {
        return NamingUtil.toSmallHump(name);
    }

    public String getLowerCaseUnderline() {
        return NamingUtil.toLowerCaseUnderline(name);
    }

    public List<FieldSchema> getFieldSchema() {
        if (CollUtil.isNotEmpty(fieldSchema)) {
            fieldSchema = fieldSchema.stream()
                .sorted(Comparator.comparing(FieldSchema::getOrderNo))
                .collect(Collectors.toList());
        }
        return fieldSchema;
    }
}