/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jad.common.base.entity.TreeNode;
import com.jad.common.base.service.TreeService;
import com.jad.common.entity.Tree;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;

/**
 * 树形数据服务类
 *
 * @author cxxwl96
 * @since 2021/8/21 12:52
 */
public class TreeServiceImpl<M extends BaseMapper<T>, T extends TreeNode<T>> extends ServiceImpl<M, T>
    implements TreeService<T> {

    /**
     * 获取树实例
     *
     * @return 树实例
     */
    @Override
    public Tree<T> getTreeBean() {
        final List<T> list = super.list();
        return new Tree<>(list, null);
    }

    /**
     * 获取完整树
     *
     * @return 完整树
     */
    @Override
    public List<T> getRootTree() {
        return getTreeBean().getRootTree();
    }

    /**
     * 获取子树，包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    @Override
    public T getSubTree(String id) {
        return getTreeBean().getSubTree(id);
    }

    /**
     * 获取子节点，包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    @Override
    public List<T> getSubList(String id) {
        return getTreeBean().getSubList(id);
    }

    /**
     * 获取子树，不包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    @Override
    public List<T> getChildrenTree(String id) {
        return getTreeBean().getChildrenTree(id);
    }

    /**
     * 获取子节点，不包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    @Override
    public List<T> getChildrenList(String id) {
        return getTreeBean().getChildrenList(id);
    }

    /**
     * 删除子树
     *
     * @param id 子树根节点id
     * @param includeSelf 是否包含子树根节点
     * @return 是否删除成功
     */
    @Override
    public boolean removeChildren(String id, boolean includeSelf) {
        boolean success = false;
        List<T> list;
        if (includeSelf) {
            list = getSubList(id);
        } else {
            list = getChildrenList(id);
        }
        if (CollUtil.isNotEmpty(list)) {
            final List<String> ids = list.stream().map(T::getId).collect(Collectors.toList());
            success = super.removeByIds(ids);
        }
        return success;
    }
}
