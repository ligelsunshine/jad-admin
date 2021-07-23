/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 菜单类型
 *
 * @author cxxwl96
 * @since 2021/7/20 23:49
 */
public enum MenuType {
    // 目录
    DIRECTORY(0),
    // 菜单
    MENU(1),
    // 按钮
    BUTTON(2);

    @EnumValue
    @JsonValue
    private final int index;

    MenuType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
