/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.system.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分配权限DTO
 *
 * @author cxxwl96
 * @since 2021/8/27 23:06
 */
@Data
public class AssignPermissionsDto {

    @Data
    public static class MenuItem {
        private String menuId;
        private boolean leaf;
    }

    @ApiModelProperty("角色ID")
    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    @ApiModelProperty("菜单ID")
    @NotEmpty(message = "至少分配一个菜单")
    private List<MenuItem> menuItems;
}
