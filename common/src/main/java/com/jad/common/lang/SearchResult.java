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
    private long page;

    @ApiModelProperty(value = "每页数量")
    private long pageSize;

    @ApiModelProperty(value = "总数量")
    private long total;

    @ApiModelProperty(value = "数据")
    private List<T> items;
}
