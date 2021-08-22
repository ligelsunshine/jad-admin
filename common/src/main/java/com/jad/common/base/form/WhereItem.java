/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.form;

import com.jad.common.enums.Condition;
import com.jad.common.function.PropertyFunc;
import com.jad.common.utils.NamingUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 过滤条件
 *
 * @author cxxwl96
 * @since 2021/8/22 16:18
 */
@ApiModel(value = "过滤条件")
@Data
@Accessors(chain = true)
public class WhereItem {
    @ApiModelProperty(value = "字段名")
    private String column;

    @ApiModelProperty(value = "条件")
    private Condition condition;

    @ApiModelProperty(value = "字段值")
    private Object value;

    /**
     * 生成where条件实例
     *
     * @param column 字段
     * @param condition 条件
     * @param value 字段值
     * @param <T> 类型
     * @return 条件实例
     */
    public <T> WhereItem whereItem(PropertyFunc<T, ?> column, Condition condition, Object value) {
        // 转数据库字段
        this.column = NamingUtil.toLowerCaseUnderline(column.getFieldName());
        this.condition = condition;
        this.value = value;
        return this;
    }

    public void setColumn(String column) {
        // 转数据库字段
        this.column = NamingUtil.toLowerCaseUnderline(this.column);
    }

    public String getColumn() {
        return column;
    }
}
