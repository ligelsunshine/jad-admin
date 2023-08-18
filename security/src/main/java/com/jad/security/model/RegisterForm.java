/*
 * Copyright (c) 2021-2023, cxxwl96.com (cxxwl96@sina.com).
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

package com.jad.security.model;

import com.jad.common.enums.UserOrigin;
import com.jad.common.valid.AddValidGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 注册表单
 *
 * @author cxxwl96
 * @since 2023/8/18 22:21
 */
@ApiModel(value = "注册表单")
@Data
@Accessors(chain = true)
public class RegisterForm {
    @NotNull(message = "注册类型不能为空 []")
    private UserOrigin type;

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    @Pattern(message = "账号只能由16位 A-Z a-z 0-9 @ . _ 组成", regexp = "^[A-Za-z0-9@._]{1,16}$")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空", groups = {AddValidGroup.class})
    @Pattern(message = "密码只能由16位 A-Z a-z 0-9 @ . _ 组成", regexp = "^[A-Za-z0-9@._]{0,16}$",
        groups = {AddValidGroup.class})
    private String password;

    @ApiModelProperty(value = "确认密码")
    @NotBlank(message = "密码不能为空", groups = {AddValidGroup.class})
    @Pattern(message = "密码只能由16位 A-Z a-z 0-9 @ . _ 组成", regexp = "^[A-Za-z0-9@._]{0,16}$",
        groups = {AddValidGroup.class})
    private String rePassword;

    @ApiModelProperty(value = "验证码key")
    @NotBlank(message = "验证码key不能为空")
    private String codeKey;

    @ApiModelProperty(value = "验证码value")
    @NotBlank(message = "验证码不能为空")
    private String codeValue;
}
