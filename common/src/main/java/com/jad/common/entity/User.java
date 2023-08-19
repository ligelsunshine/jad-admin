/*
 * Copyright (c) 2021-2021, jad (cxxwl96@sina.com).
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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import com.jad.common.enums.Sex;
import com.jad.common.enums.UserOrigin;
import com.jad.common.valid.AddValidGroup;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统用户
 *
 * @author cxxwl96
 * @since 2021-06-29
 */
@ApiModel("系统用户")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    @Pattern(message = "账号只能由16位 A-Z a-z 0-9 @ . _ 组成", regexp = "^[A-Za-z0-9@._]{1,16}$")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空", groups = {AddValidGroup.class})
    @Pattern(message = "密码只能由16位 A-Z a-z 0-9 @ . _ 组成", regexp = "^[A-Za-z0-9@._]{0,16}$",
        groups = {AddValidGroup.class})
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "用户账号来源")
    private UserOrigin origin = UserOrigin.UNKNOWN;

    @ApiModelProperty(value = "性别【未设置：0,男：1,女：2】")
    private Sex sex = Sex.UNSET;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "邮箱")
    @Pattern(message = "邮箱格式不正确", regexp = "^(\\w+@(\\w+\\.)+(\\w+))?$")
    private String email;

    @ApiModelProperty(value = "手机号码")
    @Pattern(message = "手机号码格式不正确", regexp = "^(1[345789]\\d{9})?$")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLogin;

    @ApiModelProperty(value = "部门ID")
    private String deptId;

    @ApiModelProperty(value = "部门")
    @TableField(exist = false)
    private Dept dept;

    @ApiModelProperty(value = "用户额外信息")
    @TableField(exist = false)
    private Object userExtends;

    @ApiModelProperty(value = "角色ID")
    @TableField(exist = false)
    @NotEmpty(message = "至少分配一个角色")
    private List<String> roleIds;

    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private List<Role> roles;
}
