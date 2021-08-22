/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service.impl;

import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.entity.Role;
import com.jad.common.mapper.RoleMapper;
import com.jad.common.service.RoleService;

import org.springframework.stereotype.Service;

/**
 * 角色服务实现类
 *
 * @author cxxwl96
 * @since 2021/8/22 14:34
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

}
