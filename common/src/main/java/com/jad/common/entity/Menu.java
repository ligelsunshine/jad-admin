package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.TreeNode;
import com.jad.common.enums.MenuType;
import com.jad.common.enums.Status;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class Menu extends TreeNode<Menu> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单类型 [0：目录 1：菜单 2：按钮]")
    @NotNull(message = "请选择菜单类型")
    private MenuType type;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String permissions;

    @ApiModelProperty(value = "菜单URL")
    @NotBlank(message = "路由地址不能为空", groups = {DirectoryValidGroup.class, MenuValidGroup.class})
    private String path;

    @ApiModelProperty(value = "路由名称")
    private String name = IdUtil.getSnowflake().nextIdStr();

    @ApiModelProperty(value = "前端组件URL")
    @NotBlank(message = "组件路径不能为空", groups = {MenuValidGroup.class})
    private String component;

    @ApiModelProperty(value = "重定向")
    private String redirect;

    @ApiModelProperty(value = "菜单标题")
    @NotBlank(message = "菜单标题不能为空",
        groups = {DirectoryValidGroup.class, MenuValidGroup.class, ButtonValidGroup.class})
    private String title;

    @ApiModelProperty(value = "是否固定标签")
    private Boolean affix = false;

    @ApiModelProperty(value = "是否忽略KeepAlive缓存")
    private Boolean ignoreKeepAlive = false;

    @ApiModelProperty(value = "菜单图标")
    private String icon = "";

    @ApiModelProperty(value = "内嵌iframe的地址")
    private String frameSrc;

    @ApiModelProperty(value = "指定该路由切换的动画名")
    private String transitionName;

    @ApiModelProperty(value = "如果该路由会携带参数，且需要在tab页上面显示。则需要设置为true")
    private Boolean carryParam = false;

    @ApiModelProperty(value = "是否隐藏所有子菜单")
    private Boolean hideChildrenInMenu = false;

    @ApiModelProperty(value = "当前路由不再标签页显示")
    private Boolean hideTab = false;

    @ApiModelProperty(value = "当前路由不再菜单显示")
    private Boolean hideMenu = false;

    @ApiModelProperty(value = "本路由仅用于菜单生成，不会在实际的路由表中出现")
    private Boolean ignoreRoute = false;

    @ApiModelProperty(value = "是否在子级菜单的完整path中忽略本级path")
    private Boolean hidePathForChildren = false;

    @ApiModelProperty(value = "是否外链")
    private Boolean external = false;

    @ApiModelProperty(value = "状态【启用：0,停用：1】")
    private Status status = Status.ENABLE;

    /**
     * 目录校验分组
     */
    public interface DirectoryValidGroup {
    }

    /**
     * 菜单校验分组
     */
    public interface MenuValidGroup {
    }

    /**
     * 按钮校验分组
     */
    public interface ButtonValidGroup {
    }

}
