/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.lang;

import com.jad.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页查询结果
 *
 * @author cxxwl96
 * @since 2021/8/22 18:27
 */
@Data
public class SearchResult<T extends BaseEntity> implements Serializable {
    @ApiModelProperty(value = "当前页")
    private long current;

    @ApiModelProperty(value = "每页数量")
    private long size;

    @ApiModelProperty(value = "总数量")
    private long total;

    @ApiModelProperty(value = "数据")
    private List<T> data;
}
