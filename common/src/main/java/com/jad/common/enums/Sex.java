/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 性别【未设置：0,男：1,女：2】
 *
 * @author cxxwl96
 * @since 2021/8/29 21:13
 */
public enum Sex {
    UNSET(0),
    MAN(1),
    WOMAN(2);

    @EnumValue
    @JsonValue
    private final int index;

    Sex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
