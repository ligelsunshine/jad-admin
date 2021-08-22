/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.form;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分页查询表单
 *
 * @author cxxwl96
 * @since 2021/8/22 14:11
 */

@ApiModel(value = "分页查询表单")
@Data
@Accessors(chain = true)
public class SearchForm {

    @ApiModelProperty(value = "每页数量")
    private long size;

    @ApiModelProperty(value = "当前页")
    private long current;

    @ApiModelProperty(value = "过滤条件")
    private List<WhereItem> whereItems = new ArrayList<>();

    @ApiModelProperty(value = "排序条件")
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addWhereItem(WhereItem whereItem) {
        whereItems.add(whereItem);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }
}
