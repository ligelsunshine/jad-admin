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

package com.jad.common.base.form;

import com.jad.common.function.PropertyFunc;
import com.jad.common.utils.NamingUtil;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;
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

    @ApiModelProperty(value = "当前页")
    private long page = 1;

    @ApiModelProperty(value = "每页数量")
    private long pageSize = 10;

    @ApiModelProperty(value = "过滤条件")
    private List<WhereItem> whereItems = new ArrayList<>();

    @ApiModelProperty(value = "排序条件")
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 添加where条件
     *
     * @param whereItem where条件
     */
    public void addWhereItem(WhereItem whereItem) {
        whereItems.add(whereItem);
    }

    /**
     * 删除where条件
     *
     * @param column 字段
     * @param <T> 类型
     */
    public <T> void deleteWhereItem(PropertyFunc<T, ?> column) {
        final String fieldName = NamingUtil.toLowerCaseUnderline(column.getFieldName());
        if (StrUtil.isNotBlank(fieldName)) {
            whereItems.removeIf(item -> fieldName.equals(item.getColumn()));
        }
    }

    /**
     * 添加order排序
     *
     * @param orderItem order排序
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    /**
     * 删除order排序
     *
     * @param column 字段
     * @param <T> 类型
     */
    public <T> void deleteOrderItem(PropertyFunc<T, ?> column) {
        final String fieldName = NamingUtil.toLowerCaseUnderline(column.getFieldName());
        if (StrUtil.isNotBlank(fieldName)) {
            orderItems.removeIf(item -> fieldName.equals(item.getColumn()));
        }
    }
}
