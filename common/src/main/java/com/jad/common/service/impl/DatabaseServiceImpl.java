/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.service.impl;

import com.jad.common.entity.Database;
import com.jad.common.mapper.DatabaseMapper;
import com.jad.common.service.DatabaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-29
 */
@Service
public class DatabaseServiceImpl extends ServiceImpl<DatabaseMapper, Database> implements DatabaseService {

}
