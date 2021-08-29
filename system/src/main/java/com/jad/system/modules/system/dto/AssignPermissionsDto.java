/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.system.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 分配权限DTO
 *
 * @author cxxwl96
 * @since 2021/8/27 23:06
 */
@Data
public class AssignPermissionsDto {
    public static class Item {
        @ApiModelProperty("菜单ID")
        @Getter
        @Setter
        @NotBlank(message = "菜单ID不能为空")
        private String menuId;

        @ApiModelProperty("未全选")
        @Getter
        @Setter
        private boolean halfChecked;
    }

    @ApiModelProperty("角色ID")
    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    @ApiModelProperty("权限")
    private List<Item> roleMenus;
}
