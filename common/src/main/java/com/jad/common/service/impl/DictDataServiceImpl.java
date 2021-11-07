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

import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.Dict;
import com.jad.common.entity.DictData;
import com.jad.common.exception.BadRequestException;
import com.jad.common.mapper.DictDataMapper;
import com.jad.common.service.DictDataService;
import com.jad.common.service.DictService;
import com.jad.common.utils.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 字典数据服务实现类
 *
 * @author cxxwl96
 * @since 2021/11/05 21:07
 */
@Service
public class DictDataServiceImpl extends BaseServiceImpl<DictDataMapper, DictData> implements DictDataService {
    @Autowired
    private DictService dictService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加
     *
     * @param entity entity
     * @return 是否添加成功
     */
    @Override
    public boolean save(DictData entity) {
        if (!super.save(entity)) {
            throw new BadRequestException("添加字典数据失败");
        }
        final Dict dict = dictService.getById(entity.getDictId());
        // 清空缓存
        redisUtil.hdel(RedisConst.SYSTEM_DICT, dict.getCode());
        return true;
    }

    /**
     * 删除
     *
     * @param id ID
     * @return 是否删除成功
     */
    @Override
    public boolean removeById(Serializable id) {
        final DictData dictData = getById(id);
        if (!super.removeById(id)) {
            throw new BadRequestException("删除字典数据失败");
        }
        final Dict dict = dictService.getById(dictData.getDictId());
        // 清空缓存
        redisUtil.hdel(RedisConst.SYSTEM_DICT, dict.getCode());
        return true;
    }

    /**
     * 修改
     *
     * @param entity entity
     * @return 是否修改成功
     */
    @Override
    public boolean updateById(DictData entity) {
        if (!super.updateById(entity)) {
            throw new BadRequestException("修改字典数据失败");
        }
        final Dict dict = dictService.getById(entity.getDictId());
        // 清空缓存
        redisUtil.hdel(RedisConst.SYSTEM_DICT, dict.getCode());
        return true;
    }
}
