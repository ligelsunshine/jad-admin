/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.entity;

import com.jad.common.base.entity.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;

/**
 * 树形数据处理
 *
 * @author cxxwl96
 * @since 2021/8/18 22:35
 */
public class Tree<T extends TreeNode<T>> {
    private List<T> list;

    private List<T> tree;

    private String rootValue;

    /**
     * 构建树形数据
     *
     * @param list 数据列表
     * @param rootValue 根节点的值
     */
    public Tree(List<T> list, String rootValue) {
        this.list = CollUtil.newArrayList(list); // new instance
        this.tree = new ArrayList<>();
        this.rootValue = rootValue;
        this.build();
    }

    /**
     * 获取完整树形数据
     *
     * @return 完整树形数据
     */
    public List<T> getAllTree() {
        return this.tree;
    }

    /**
     * 获取子树
     *
     * @param id 子树根节点id
     * @return 子树
     */
    public T getSubTree(String id) {
        return findSubTree(this.tree, id);
    }

    public List<T> getChildren(String id) {
        final T subTree = findSubTree(this.tree, id);
        if (subTree != null) {
            return subTree.getChildren();
        }
        return null;
    }

    /**
     * 将树形数据转为集合数据
     *
     * @param treeNode 树形数据
     * @return 数据列表
     */
    public List<T> toList(T treeNode) {
        List<T> list = new ArrayList<>();
        final List<T> childrenList = toList(treeNode.getChildren());
        treeNode.setChildren(null);
        list.add(treeNode);
        if (childrenList != null) {
            list.addAll(childrenList);
        }
        return list;
    }

    /**
     * 将树形数据转为集合数据
     *
     * @param treeList 树形数据
     * @return 数据列表
     */
    public List<T> toList(List<T> treeList) {
        if (treeList == null) {
            return null;
        } else if (treeList.size() == 0) {
            return treeList;
        }
        List<T> list = new ArrayList<>();
        for (T item : treeList) {
            if (CollUtil.isNotEmpty(item.getChildren())) {
                list.addAll(toList(item.getChildren()));
            } else {
                item.setChildren(null);
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 构建树形数据
     */
    private void build() {
        // 查找根节点
        list.forEach(node -> {
            if (rootValue == null && node.getPId() == null) {
                tree.add(node);
            } else if (rootValue != null && rootValue.equals(node.getPId())) {
                tree.add(node);
            }
        });
        // 排序
        this.tree = this.tree.stream().sorted(Comparator.comparingInt(T::getOrderNo)).collect(Collectors.toList());
        // 查找子节点
        this.tree.forEach(node -> {
            node.setChildren(findChildren(node.getId()));
        });
    }

    private List<T> findChildren(String pId) {
        List<T> children = new ArrayList<>();
        // 判断是否还有子节点
        final Optional<T> optional = this.list.stream().filter(item -> pId.equals(item.getPId())).findFirst();
        if (!optional.isPresent()) {
            return null;
        }
        // 查找子节点
        this.list.forEach(item -> {
            if (pId.equals(item.getPId())) {
                children.add(item);
            }
        });
        for (T child : children) {
            child.setChildren(findChildren(child.getId()));
        }
        return children;
    }

    private T findSubTree(List<T> treeList, String id) {
        if (CollUtil.isEmpty(treeList)) {
            return null;
        }
        for (T node : treeList) {
            if (id.equals(node.getId())) {
                return node;
            } else {
                return findSubTree(node.getChildren(), id);
            }
        }
        return null;
    }
}
