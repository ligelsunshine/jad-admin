/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.entity;

import com.jad.common.enums.MenuType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * MenuTree
 *
 * @author cxxwl96
 * @since 2021/7/20 21:59
 */
@ApiModel("菜单树")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MenuTree extends Tree {

    @ApiModelProperty(value = "类型 [0：目录 1：菜单 2：按钮]")
    private MenuType type;

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

    @ApiModelProperty(value = "是否忽略KeepAlive缓存")
    private Boolean ignoreKeepAlive;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "内嵌iframe的地址")
    private String frameSrc;

    @ApiModelProperty(value = "指定该路由切换的动画名")
    private String transitionName;

    @ApiModelProperty(value = "如果该路由会携带参数，且需要在tab页上面显示。则需要设置为true")
    private Boolean carryParam;

    @ApiModelProperty(value = "是否隐藏所有子菜单")
    private Boolean hideChildrenInMenu;

    @ApiModelProperty(value = "当前路由不再标签页显示")
    private Boolean hideTab;

    @ApiModelProperty(value = "当前路由不再菜单显示")
    private String hideMenu;

    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    @ApiModelProperty(value = "本路由仅用于菜单生成，不会在实际的路由表中出现")
    private Boolean ignoreRoute;

    @ApiModelProperty(value = "是否在子级菜单的完整path中忽略本级path")
    private Boolean hidePathForChildren;
}



