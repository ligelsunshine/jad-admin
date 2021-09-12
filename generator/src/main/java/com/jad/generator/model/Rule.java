/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.model;

import com.jad.generator.enums.RuleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 自定义规则
 *
 * @author cxxwl96
 * @since 2021/9/12 14:29
 */
@Data
public class Rule {
    // 规则类型
    @NotNull(message = "请选择规则类型")
    private RuleType type;

    // 错误消息
    @NotBlank(message = "错误消息不能为空")
    private String message;

    // 长度
    private Integer len;

    // 最大值
    private Integer max;

    // 最小值
    private Integer min;

    // 正则
    private String pattern;
}
