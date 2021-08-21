package com.jad.common.service.impl;

import com.jad.common.base.service.impl.TreeServiceImpl;
import com.jad.common.entity.Dept;
import com.jad.common.mapper.DeptMapper;
import com.jad.common.service.DeptService;

import org.springframework.stereotype.Service;

/**
 * 部门服务实现类
 *
 * @author cxxwl96
 * @since 2021/8/21 14:05
 */
@Service
public class DeptServiceImpl extends TreeServiceImpl<DeptMapper, Dept> implements DeptService {
}
