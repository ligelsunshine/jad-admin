package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.jad.common.base.entity.BaseEntity;
import com.jad.common.enums.MenuType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxxwl96
 * @since 2021-07-13
 */
@ApiModel("菜单")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级菜单ID")
    private String pId;

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

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<Menu> children;
}
