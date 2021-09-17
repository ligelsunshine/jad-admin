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

package com.jad.slave.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import com.jad.slave.enums.Sex;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 学生实体
 *
 * @author cxxwl96
 * @since 2021/09/16 23:36
 */
@ApiModel("学生实体")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("slave_student")
public class Student extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否已婚")
    @NotNull(message = "是否已婚不能为空")
    private Boolean isMarry = true;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    @Pattern(message = "姓名长度必须在4-7之间", regexp = "^[^S]{4,7}$")
    private String name = "cxx";

    @ApiModelProperty(value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age = 0;

    @ApiModelProperty(value = "身高")
    private float myHeight = 1.65f;

    @ApiModelProperty(value = "生日")
    @NotNull(message = "生日不能为空")
    private LocalDateTime birthday = LocalDateTimeUtil.parse("2021-09-12T21:37:47");

    @ApiModelProperty(value = "性别")
    @NotNull(message = "性别不能为空")
    private Sex sex;

    @ApiModelProperty(value = "余额")
    private double myMoney = 1.99;

    @ApiModelProperty(value = "是否启用【未启用：0, 已启用：1（默认）】")
    @TableField(value = "enable", updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT)
    @TableLogic(value = "1", delval = "0")
    private Boolean enable;
}
