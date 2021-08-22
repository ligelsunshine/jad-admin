/*
 * Copyright (C), 2021-2021, jad
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
    LIKE("like");

    private final String condition;

    Condition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
