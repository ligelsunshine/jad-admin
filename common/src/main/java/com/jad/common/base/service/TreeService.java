/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.service;

import com.jad.common.base.entity.TreeNode;
import com.jad.common.entity.Tree;

import java.util.List;

/**
 * 树形数据服务接口类
 *
 * @author cxxwl96
 * @since 2021/8/21 13:26
 */
public interface TreeService<T extends TreeNode<T>> extends BaseService<T> {

    /**
     * 获取树实例
     *
     * @return 树实例
     */
    Tree<T> getTreeBean();

    /**
     * 获取完整树
     *
     * @return 完整树
     */
    List<T> getRootTree();

    /**
     * 获取子树，包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    T getSubTree(String id);

    /**
     * 获取子节点，包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    List<T> getSubList(String id);

    /**
     * 获取子树，不包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    List<T> getChildrenTree(String id);

    /**
     * 获取子节点，不包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    List<T> getChildrenList(String id);

    /**
     * 删除子树
     *
     * @param id 子树根节点id
     * @param includeSelf 是否包含子树
     * @return 是否删除成功
     */
    boolean removeTree(String id, boolean includeSelf);
}
