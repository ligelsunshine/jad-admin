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

package com.jad.common.model.dto;

import com.jad.common.enums.Sex;

import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统用户基础信息
 *
 * @author cxxwl96
 * @since 2023/4/10 22:08
 */
@ApiModel("系统用户基础信息")
@Data
@Accessors(chain = true)
public class UserBaseInfoDto {
    @ApiModelProperty(value = "姓名")
    private String name;

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
}
