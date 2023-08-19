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

package com.jad.common.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * TreeNode
 *
 * @author cxxwl96
 * @since 2021/8/18 22:21
 */
@ApiModel(value = "树形结构节点")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TreeNode<T> extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级节点ID")
    private String pId = null;

    @ApiModelProperty(value = "排序")
    private Integer orderNo = 0;

    @ApiModelProperty(value = "编码")
    @Pattern(message = "编码仅数字、字母、中横线组成", regexp = "^[a-zA-Z0-9\\-]*$")
    private String code = null;

    @ApiModelProperty(value = "子节点")
    @TableField(exist = false)
    private List<T> children = null;

}

