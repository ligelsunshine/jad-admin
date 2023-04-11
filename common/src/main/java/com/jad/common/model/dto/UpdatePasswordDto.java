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

package com.jad.common.model.dto;

import com.jad.common.exception.BadRequestException;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 更新用户密码
 *
 * @author cxxwl96
 * @since 2023/4/11 21:57
 */
@ApiModel("更新用户密码")
@Data
@Accessors(chain = true)
public class UpdatePasswordDto {
    @ApiModelProperty(value = "旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty(value = "确认密码")
    @NotBlank(message = "两次输入的密码不一致")
    private String confirmPassword;

    public void validateConfirm() {
        if (confirmPassword == null || !confirmPassword.equals(newPassword)) {
            throw new BadRequestException("两次输入的密码不一致");
        }
    }
}
