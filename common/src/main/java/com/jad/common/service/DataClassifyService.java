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

package com.jad.common.service;

import com.jad.common.base.service.TreeService;
import com.jad.common.entity.DataClassify;
import com.jad.common.lang.Result;

/**
 * 数据分类服务接口类
 *
 * @author cxxwl96
 * @since 2021/11/12 23:21
 */
public interface DataClassifyService extends TreeService<DataClassify> {
    /**
     * 获取数据分类
     *
     * @param code 编码
     * @param includeSelf 是否包含本身
     * @return 数据分类树
     */
    Result<?> getTree(String code, boolean includeSelf);
}
