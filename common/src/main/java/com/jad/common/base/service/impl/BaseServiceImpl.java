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
import com.jad.common.function.PropertyFunc;
import com.jad.common.lang.SearchResult;
import com.jad.common.utils.NamingUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public SearchResult<T> getPageList(SearchForm searchForm) {
        // 生成QueryWrapper条件
        QueryWrapper<T> qw = generateQueryWrapper(searchForm);
        // 生成Pager分页
        Page<T> pager = generatePager(searchForm);
        // 分页条件查询
        pager = super.page(pager, qw);
        // 封装返回数据
        final SearchResult<T> searchResult = new SearchResult<>();
        searchResult.setPage(pager.getCurrent());
        searchResult.setPageSize(pager.getSize());
        searchResult.setTotal(pager.getTotal());
        searchResult.setItems(pager.getRecords());

        return searchResult;
    }

    /**
     * 生成Pager分页
     *
     * @param searchForm 查询表单
     * @return Pager分页
     */
    private Page<T> generatePager(SearchForm searchForm) {
        Page<T> pager = new Page<>(searchForm.getPage(), searchForm.getPageSize(), true);
        if (searchForm.getOrderItems() == null) {
            return pager;
        }
        // 若果没有order by，默认添加时间降序
        if (searchForm.getOrderItems().size() == 0) {
            // 默认创建时间降序
            searchForm.addOrderItem(
                new com.jad.common.base.form.OrderItem().orderItem(BaseEntity::getCreateTime, false));
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
                    case RANGE_TIME:
                        // 创建时间范围查询
                        final List<?> value = (List<?>) item.getValue();
                        if (value.size() == 2) {
                            PropertyFunc<T, ?> column = BaseEntity::getCreateTime;
                            String createTimeFiled = NamingUtil.toLowerCaseUnderline(column.getFieldName());
                            try {
                                // 修改时间部分为 00:00:00, 第二个日期+1天
                                final String startTimeStr = (String) value.get(0);
                                final String endTimeStr = (String) value.get(1);

                                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                final Date startTime = sdf.parse(startTimeStr);
                                Date endTime = sdf.parse(endTimeStr);
                                // 日期+1天
                                final Calendar calendar = Calendar.getInstance();
                                calendar.setTime(endTime);
                                calendar.add(Calendar.DAY_OF_MONTH, 1);
                                endTime = calendar.getTime();

                                qw.ge(createTimeFiled, startTime);
                                qw.le(createTimeFiled, endTime);
                            } catch (ClassCastException | ParseException e) {
                                // 不做任何处理
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        }
        return qw;
    }
}
