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

package com.jad.common.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.jad.common.base.entity.TreeNode;
import com.jad.common.base.service.TreeService;
import com.jad.common.entity.Tree;
import com.jad.common.exception.BadRequestException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 树形数据服务类
 *
 * @author cxxwl96
 * @since 2021/8/21 12:52
 */
public class TreeServiceImpl<M extends BaseMapper<T>, T extends TreeNode<T>> extends BaseServiceImpl<M, T>
    implements TreeService<T> {
    @Override
    public boolean save(T entity) {
        checkCode(entity);
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        checkCode(entityList);
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        checkCode(entity);
        return super.saveOrUpdate(entity);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        checkCode(entityList);
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    @Override
    public boolean saveBatch(Collection<T> entityList) {
        checkCode(entityList);
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        checkCode(entityList);
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    public boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper) {
        checkCode(entity);
        return super.saveOrUpdate(entity, updateWrapper);
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        checkCode(entityList);
        return super.updateBatchById(entityList, batchSize);
    }

    @Override
    public boolean updateById(T entity) {
        checkCode(entity);
        return super.updateById(entity);
    }

    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        checkCode(entity);
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList) {
        checkCode(entityList);
        return super.updateBatchById(entityList);
    }

    /**
     * 获取树实例
     *
     * @return 树实例
     */
    @Override
    public Tree<T> getTree() {
        // 加上.setEntityClass(super.getEntityClass())，防止can not find lambda cache for this entity
        final List<T> list = super.lambdaQuery()
            .setEntityClass(super.getEntityClass())
            .orderByAsc(T::getCreateTime)
            .list();
        return new Tree<>(list, null);
    }

    /**
     * 获取完整树
     *
     * @return 完整树
     */
    @Override
    public List<T> getRootTree() {
        return getTree().getRootTree();
    }

    /**
     * 获取子树，包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    @Override
    public T getSubTree(String id) {
        return getTree().getSubTree(id);
    }

    /**
     * 获取子节点，包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    @Override
    public List<T> getSubList(String id) {
        return getTree().getSubList(id);
    }

    /**
     * 获取子树，不包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    @Override
    public List<T> getChildrenTree(String id) {
        return getTree().getChildrenTree(id);
    }

    /**
     * 获取子节点，不包含子树根节点
     *
     * @param id 子树根节点id
     * @return 子树
     */
    @Override
    public List<T> getChildrenList(String id) {
        return getTree().getChildrenList(id);
    }

    /**
     * 删除子树
     *
     * @param id 子树根节点id
     * @param includeSelf 是否包含子树根节点
     * @return 是否删除成功
     */
    @Override
    public boolean removeTree(String id, boolean includeSelf) {
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

    /**
     * 编码是否唯一
     *
     * @param treeNode 节点
     * @return 编码是否唯一
     */
    @Override
    public boolean isUniqueCode(T treeNode) {
        if (StrUtil.isBlank(treeNode.getCode())) {
            return true; // 编码为空不检查
        }
        // 检查树节点的code是否唯一
        // 加上.setEntityClass(super.getEntityClass())，防止can not find lambda cache for this entity
        LambdaQueryChainWrapper<T> wrapper = super.lambdaQuery()
            .setEntityClass(super.getEntityClass())
            .eq(T::getCode, treeNode.getCode());
        if (StrUtil.isNotBlank(treeNode.getId())) {
            wrapper.ne(T::getId, treeNode.getId());
        }
        return wrapper.count() == 0;
    }

    protected void checkCode(Collection<T> entityList) {
        for (T entity : entityList) {
            checkCode(entity);
        }
    }

    protected void checkCode(T entity) {
        Assert.isTrue(isUniqueCode(entity), () -> new BadRequestException("编码" + entity.getCode() + "已经存在"));
    }
}
