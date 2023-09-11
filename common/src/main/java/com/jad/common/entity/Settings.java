/*
 * Copyright (c) 2021-2023, jad (cxxwl96@sina.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.TreeNode;
import com.jad.common.enums.Component;
import com.jad.common.enums.SettingType;
import com.jad.common.enums.Status;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统设置管理实体
 *
 * @author cxxwl96
 * @since 2023/08/28 22:24
 */
@ApiModel("系统设置管理实体")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_settings")
public class Settings extends TreeNode<Settings> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否根节点")
    private boolean origin;

    @ApiModelProperty(value = "设置类型")
    @NotNull(message = "设置类型不能为空")
    private SettingType settingType = SettingType.DIRECTORY;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String permissions;

    @ApiModelProperty(value = "状态【启用：0,停用：1】")
    private Status status = Status.ENABLE;

    @ApiModelProperty(value = "提示消息")
    private String helpMessage;

    @ApiModelProperty(value = "组件")
    @NotNull(message = "组件不能为空")
    private Component component = Component.INPUT;

    @ApiModelProperty(value = "默认值")
    private String val;

    @ApiModelProperty(value = "是否必须")
    private boolean required;

    @ApiModelProperty(value = "自定义校验规则")
    private String rules;

    @ApiModelProperty(value = "组件属性")
    private String componentProps;

    @ApiModelProperty(value = "列属性")
    private String colProps;

}
