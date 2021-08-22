/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.form;

import com.jad.common.function.PropertyFunc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 排序条件
 *
 * @author cxxwl96
 * @since 2021/8/22 16:18
 */
@ApiModel(value = "排序条件")
@Data
@Accessors(chain = true)
public class OrderItem {
    @ApiModelProperty(value = "字段名")
    private String column;

    @ApiModelProperty(value = "升序？")
    private boolean asc;

    /**
     * 生成order实例
     *
     * @param column 字段
     * @param asc 升序？
     * @param <T> 类型
     * @return 条件实例
     */
    public <T> OrderItem orderItem(PropertyFunc<T, ?> column, boolean asc) {
        // TODO 转数据库字段
        this.column = column.getFieldName();
        this.asc = asc;
        return this;
    }

    public String getColumn() {
        // TODO 转数据库字段
        return column;
    }
}
