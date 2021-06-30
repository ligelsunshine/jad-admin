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
@TableName("sys_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;


}
