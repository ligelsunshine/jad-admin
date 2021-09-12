/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.generator.model;

import com.jad.common.utils.NamingUtil;

import lombok.Data;

/**
 * 枚举类型的枚举值
 *
 * @author cxxwl96
 * @since 2021/9/12 23:04
 */
@Data
public class EnumVal {
    // 中文名
    private String title;

    // 枚举对象名
    private String name;

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
