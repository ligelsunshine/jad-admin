/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("form_table")
public class Table extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库id
     */
    private String databaseId;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 中文名称
     */
    private String name;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 表单名称（命名空间_实体名称）
     */
    private String tableName;

    /**
     * 是否逻辑删除
     */
    private Boolean isld;

    /**
     * 是否作为外键表
     */
    private Boolean isfk;


}
