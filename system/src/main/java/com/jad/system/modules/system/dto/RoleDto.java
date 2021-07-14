/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.system.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RoleDto
 *
 * @author cxxwl96
 * @since 2021/7/11 19:11
 */
@ApiModel("角色")
@Data
public class RoleDto {
    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色级别")
    private Integer level;

    @ApiModelProperty(value = "角色描述")
    private String description;
}
