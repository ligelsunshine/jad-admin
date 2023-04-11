/*
 * Copyright (C), 2021-2021, jad
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
