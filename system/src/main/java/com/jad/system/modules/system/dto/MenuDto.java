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
@ApiModel("菜单权限")
@Data
public class MenuDto {
    @ApiModelProperty(value = "父级菜单ID")
    private String pId;

    @ApiModelProperty(value = "类型 [0：目录 1：菜单 2：按钮]")
    private Integer type;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String permissions;

    @ApiModelProperty(value = "菜单URL")
    private String path;

    @ApiModelProperty(value = "路由名称")
    private String name;

    @ApiModelProperty(value = "前端组件URL")
    private String component;

    @ApiModelProperty(value = "重定向")
    private String redirect;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "是否固定标签")
    private Boolean affix;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "是否隐藏所有子菜单")
    private Boolean hideChildrenInMenu;

    @ApiModelProperty(value = "排序")
    private Integer orderNo;
}
