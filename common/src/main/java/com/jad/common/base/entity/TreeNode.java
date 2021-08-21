/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

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
    private String code = null;

    @ApiModelProperty(value = "子节点")
    @TableField(exist = false)
    private List<T> children = null;

}

