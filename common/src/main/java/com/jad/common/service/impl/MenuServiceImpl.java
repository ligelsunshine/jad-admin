/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jad.common.entity.Menu;
import com.jad.common.entity.RoleMenu;
import com.jad.common.mapper.MenuMapper;
import com.jad.common.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jad.common.service.RoleMenuService;
import com.jad.common.service.UserRoleService;
import com.jad.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
