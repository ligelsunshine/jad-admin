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
@TableName("sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色级别
     */
    private Integer level;

    /**
     * 角色描述
     */
    private String description;


}
