package com.jad.common.service;

import com.jad.common.base.service.TreeService;
import com.jad.common.entity.Dept;

/**
 * 部门服务接口类
 *
 * @author cxxwl96
 * @since 2021/8/21 14:06
 */
public interface DeptService extends TreeService<Dept> {
    /**
     * 添加部门
     *
     * @param dept 部门
     * @return 是否添加成功
     */
    @Override
    boolean save(Dept dept);

    /**
     * 修改部门
     *
     * @param dept 部门
     * @return 是否修改成功
     */
    @Override
    boolean updateById(Dept dept);
}
