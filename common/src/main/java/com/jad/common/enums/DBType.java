/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.enums;

/**
 * 数据库类型 枚举
 *
 * @author cxxwl96
 * @since 2021/6/30 00:44
 */
public enum DBType {
    Oracle("oracle.jdbc.driver.OracleDriver"),

    MySQL("com.mysql.cj.jdbc.Driver"),

    DB2("com.ibm.db2.app.DB2Driver"),

    PostgreSQL("org.postgresql.Driver"),

    H2("org.h2.Driver");

    private final String driver;

    DBType(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
