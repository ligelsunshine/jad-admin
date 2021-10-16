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
public enum Database {
    Oracle("oracle:thin", "oracle.jdbc.driver.OracleDriver"),
    MySQL("mysql", "com.mysql.cj.jdbc.Driver"),
    DB2("db2", "com.ibm.db2.app.DB2Driver"),
    PostgreSQL("postgresql", "org.postgresql.Driver"),
    H2("h2", "org.h2.Driver");

    private final String type;

    private final String driver;

    Database(String type, String driver) {
        this.type = type;
        this.driver = driver;
    }

    public String getType() {
        return type;
    }

    public String getDriver() {
        return driver;
    }
}
