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

package com.jad.common.service.impl;

import com.jad.common.base.service.impl.TreeServiceImpl;
import com.jad.common.entity.DataClassify;
import com.jad.common.lang.Result;
import com.jad.common.mapper.DataClassifyMapper;
import com.jad.common.service.DataClassifyService;

import org.springframework.stereotype.Service;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 数据分类服务实现类
 *
 * @author cxxwl96
 * @since 2021/11/12 23:21
 */
@Service
public class DataClassifyServiceImpl extends TreeServiceImpl<DataClassifyMapper, DataClassify>
    implements DataClassifyService {

    /**
     * 获取数据分类
     *
     * @param code 编码
     * @param includeSelf 是否包含本身
     * @return 数据分类树
     */
    @Override
    public Result getTree(String code, boolean includeSelf) {
        if (StrUtil.isBlank(code)) {
            return Result.success(super.getRootTree());
        }
        // 通过编码查询
        final List<DataClassify> list = super.lambdaQuery()
            .eq(DataClassify::getCode, code)
            .select(DataClassify::getId)
            .list();
        if (CollUtil.isEmpty(list)) {
            return Result.formatFailed("编码%s的数据分类不存在", code);
        }
        if (list.size() > 1) {
            return Result.formatFailed("编码%s的数据分类存在多个", code);
        }
        // 得到ID
        final String id = list.get(0).getId();
        if (includeSelf) {
            return Result.success(super.getSubTree(id));
        }
        return Result.success(super.getChildrenTree(id));
    }
}
