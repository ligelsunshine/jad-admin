/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 树结构
 *
 * @author cxxwl96
 * @since 2021/7/20 22:03
 */
@ApiModel("树结构")
@Data
@Accessors(chain = true)
public class Tree {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "父级ID")
    private String pId;

    @ApiModelProperty(value = "子集")
    private List<Tree> children;

}
