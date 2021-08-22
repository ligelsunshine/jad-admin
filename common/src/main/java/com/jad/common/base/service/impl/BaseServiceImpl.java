/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jad.common.base.entity.BaseEntity;
import com.jad.common.base.form.SearchForm;
import com.jad.common.base.form.WhereItem;
import com.jad.common.base.service.BaseService;
import com.jad.common.lang.SearchResult;

import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * 服务基类
 *
 * @author cxxwl96
 * @since 2021/8/22 18:17
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T>
    implements BaseService<T> {
    /**
     * 是否存在
     *
     * @param id ID
     * @return 是否存在
     */
    @Override
    public boolean exist(String id) {
        return super.getById(id) != null;
    }

    /**
     * 分页条件查询
     *
     * @param searchForm 查询表单
     * @return 数据
     */
    @Override
    public SearchResult<T> getListPage(SearchForm searchForm) {
        // 生成QueryWrapper条件
        QueryWrapper<T> qw = generateQueryWrapper(searchForm);
        // 生成Pager分页
        Page<T> pager = generatePager(searchForm);
        // 分页条件查询
        pager = super.page(pager, qw);
        // 封装返回数据
        final SearchResult<T> searchResult = new SearchResult<>();
        searchResult.setCurrent(pager.getCurrent());
        searchResult.setSize(pager.getSize());
        searchResult.setTotal(pager.getTotal());
        searchResult.setData(pager.getRecords());

        return searchResult;
    }

    /**
     * 生成Pager分页
     *
     * @param searchForm 查询表单
     * @return Pager分页
     */
    private Page<T> generatePager(SearchForm searchForm) {
        Page<T> pager = new Page<>(searchForm.getCurrent(), searchForm.getSize(), true);
        if (searchForm.getOrderItems() == null) {
            return pager;
        }
        for (com.jad.common.base.form.OrderItem item : searchForm.getOrderItems()) {
            pager.addOrder(new OrderItem(item.getColumn(), item.isAsc()));
        }
        return pager;
    }

    /**
     * 生成QueryWrapper条件
     *
     * @param searchForm 查询表单
     * @return QueryWrapper条件
     */
    private QueryWrapper<T> generateQueryWrapper(SearchForm searchForm) {
        final QueryWrapper<T> qw = new QueryWrapper<>();
        List<WhereItem> whereItems = searchForm.getWhereItems();
        if (whereItems == null) {
            return qw;
        }
        for (WhereItem item : whereItems) {
            if (StrUtil.isNotBlank(item.getColumn()) && item.getCondition() != null && item.getValue() != null) {
                switch (item.getCondition()) {
                    case EQ:
                        qw.eq(item.getColumn(), item.getValue());
                        break;
                    case NE:
                        qw.ne(item.getColumn(), item.getValue());
                        break;
                    case GT:
                        qw.gt(item.getColumn(), item.getValue());
                        break;
                    case GE:
                        qw.ge(item.getColumn(), item.getValue());
                        break;
                    case LT:
                        qw.lt(item.getColumn(), item.getValue());
                        break;
                    case LE:
                        qw.le(item.getColumn(), item.getValue());
                        break;
                    case LIKE:
                        qw.like(item.getColumn(), item.getValue());
                        break;
                }
            }
        }
        return qw;
    }
}
