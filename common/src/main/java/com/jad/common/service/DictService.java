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

import com.jad.common.base.service.BaseService;
import com.jad.common.entity.Dict;
import com.jad.common.entity.DictData;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 字典服务接口类
 *
 * @author cxxwl96
 * @since 2021/11/05 21:07
 */
public interface DictService extends BaseService<Dict> {

    /**
     * 删除
     *
     * @param id ID
     * @return 是否删除成功
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 删除
     *
     * @param ids IDS
     * @return 是否删除成功
     */
    @Override
    boolean removeByIds(Collection<? extends Serializable> ids);

    /**
     * 修改
     *
     * @param entity entity
     * @return 是否修改成功
     */
    @Override
    boolean updateById(Dict entity);

    /**
     * 通过字典编码获取字典
     *
     * @param code 字典编码
     * @return 字典
     */
    Dict getDictByCode(String code);

    /**
     * 通过字典编码获取字典数据
     *
     * @param code 字典编码
     * @return 字典数据
     */
    List<DictData> getDictDataByCode(String code);
}
