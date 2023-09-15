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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.Dict;
import com.jad.common.entity.DictData;
import com.jad.common.exception.BadRequestException;
import com.jad.common.mapper.DictMapper;
import com.jad.common.service.DictDataService;
import com.jad.common.service.DictService;
import com.jad.common.utils.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典服务实现类
 *
 * @author cxxwl96
 * @since 2021/11/05 21:07
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    private DictDataService dictDataService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 删除
     *
     * @param id ID
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean removeById(Serializable id) {
        final Dict dict = getById(id);
        if (id == null || !super.removeById(id)) {
            throw new BadRequestException("删除字典失败");
        }
        final LambdaQueryWrapper<DictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictData::getDictId, id);
        if (!dictDataService.remove(wrapper)) {
            throw new BadRequestException("删除字典数据失败");
        }
        // 清空缓存
        redisUtil.hdel(RedisConst.SYSTEM_DICT, dict.getCode());
        return true;
    }

    /**
     * 删除
     *
     * @param ids IDS
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean removeByIds(Collection<?> ids) {
        // 得到被删除的code
        final List<String> codes = super.lambdaQuery()
            .in(Dict::getId, ids)
            .list()
            .stream()
            .map(Dict::getCode)
            .collect(Collectors.toList());
        if (ids == null || !super.removeByIds(ids)) {
            throw new BadRequestException("删除字典失败");
        }
        final LambdaQueryWrapper<DictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(DictData::getDictId, ids);
        if (!dictDataService.remove(wrapper)) {
            throw new BadRequestException("删除字典数据失败");
        }
        // 清空缓存
        redisUtil.hdel(RedisConst.SYSTEM_DICT, codes);
        return true;
    }

    /**
     * 修改
     *
     * @param entity entity
     * @return 是否修改成功
     */
    @Override
    public boolean updateById(Dict entity) {
        final Dict dict = getById(entity.getId());
        if (!super.updateById(entity)) {
            throw new BadRequestException("修改字典失败");
        }
        // 清空缓存
        redisUtil.hdel(RedisConst.SYSTEM_DICT, dict.getCode());
        return true;
    }

    /**
     * 通过字典编码获取字典
     *
     * @param code 字典编码
     * @return 字典
     */
    @Override
    public Dict getDictByCode(String code) {
        Dict dict;
        if (redisUtil.hHasKey(RedisConst.SYSTEM_DICT, code)) {
            dict = (Dict) redisUtil.hget(RedisConst.SYSTEM_DICT, code);
            return dict;
        }
        final List<Dict> dictList = this.lambdaQuery().eq(Dict::getCode, code).list();
        if (dictList.size() == 0) {
            throw new BadRequestException("字典 %s 不存在", code);
        }
        if (dictList.size() > 1) {
            throw new BadRequestException("字典不唯一");
        }
        dict = dictList.get(0);
        final List<DictData> dictDataList = dictDataService.lambdaQuery()
            .eq(DictData::getDictId, dict.getId())
            .orderByAsc(DictData::getValue)
            .list(); // 按数据值升序排序
        dict.setData(dictDataList);
        // 缓存
        redisUtil.hset(RedisConst.SYSTEM_DICT, code, dict);
        return dict;
    }

    /**
     * 通过字典编码获取字典数据
     *
     * @param code 字典编码
     * @return 字典数据
     */
    @Override
    public List<DictData> getDictDataByCode(String code) {
        return getDictByCode(code).getData();
    }
}
