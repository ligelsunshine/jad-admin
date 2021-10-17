/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 数据库类型 枚举
 *
 * @author cxxwl96
 * @since 2021/6/30 00:44
 */
public enum DBType {
    Oracle(0, "oracle.jdbc.driver.OracleDriver"),

    MySQL(1, "com.mysql.cj.jdbc.Driver"),

    DB2(2, "com.ibm.db2.app.DB2Driver"),

    PostgreSQL(3, "org.postgresql.Driver"),

    H2(4, "org.h2.Driver");

    private final String driver;

    @EnumValue
    @JsonValue
    private final int index;

    public int getIndex() {
        return index;
    }

    DBType(int index, String driver) {
        this.index = index;
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
