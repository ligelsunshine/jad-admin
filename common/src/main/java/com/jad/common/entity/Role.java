/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import com.jad.common.enums.Status;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色
 *
 * @author cxxwl96
 * @since 2021/8/22 13:33
 */
@ApiModel("角色")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty(value = "角色编码")
    @NotBlank(message = "角色编码不能为空")
    @Pattern(regexp = "^(?![rR][oO][lL][eE]_?)[a-zA-Z0-9]{1,20}$",
        message = "角色编码仅数字或字母组成的20位以内字符串，且不能以“role“、“role_“、“ROLE“、“ROLE_“、“roLe“等不区分大小写的字样开头")
    private String code;

    @ApiModelProperty(value = "角色级别")
    private Integer level = 0;

    @ApiModelProperty(value = "是否默认角色【否：0,是：1】")
    private Boolean defaultRole = false;

    @ApiModelProperty(value = "状态【启用：0,停用：1】")
    private Status status = Status.ENABLE;

    @ApiModelProperty(value = "角色描述")
    private String description;
}
