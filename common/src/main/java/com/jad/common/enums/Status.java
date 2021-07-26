/*
 * Copyright (C), 2021-2021, jad
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
