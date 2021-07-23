/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-29
 */
@ApiModel("数据库")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("db_database")
public class Database extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库中文名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 用户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接字符串
     */
    private String url;

    /**
     * 驱动类
     */
    private String driverClassName;

    /**
     * 是否是系统数据库
     */
    private Boolean isSysDb;


}
