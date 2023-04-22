/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.service;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jad.common.base.entity.BaseEntity;
import com.jad.common.base.form.SearchForm;
import com.jad.common.lang.SearchResult;

/**
 * 服务接口类
 *
 * @author cxxwl96
 * @since 2021/8/22 18:15
 */
public interface BaseService<T extends BaseEntity> extends IService<T> {

    /**
     * 是否存在
     *
     * @param id ID
     * @return 是否存在
     */
    boolean exist(String id);

    /**
     * 分页条件查询
     *
     * @param searchForm 查询表单
     * @return 数据
     */
    SearchResult<T> getPageList(SearchForm searchForm);

    /**
     * 分页条件查询
     *
     * @param page 当前页
     * @param pageSize 每页数量
     * @param queryWrapper 查询条件
     * @return 数据
     */
    SearchResult<T> getPageList(long page, long pageSize, Wrapper<T> queryWrapper);

    /**
     * 生成Wrapper
     *
     * @param searchForm 查询表单
     * @return Wrapper
     */
    <E extends AbstractWrapper<T, String, E>> E generateWrapper(SearchForm searchForm, E wrapper);
}
