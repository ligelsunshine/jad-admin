/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jad.common.entity.User;
import com.jad.common.service.UserService;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mybatis字段自动填充
 *
 * @author cxxwl96
 * @since 2021/6/28 22:45
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private UserService userService;

    @Override
    public void insertFill(MetaObject metaObject) {
        final User user = userService.getCurrentAuthUser();
        String userId = user != null ? user.getId() : null;
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "createBy", () -> userId, String.class);

        this.strictInsertFill(metaObject, "enable", () -> true, Boolean.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        final User user = userService.getCurrentAuthUser();
        String userId = user != null ? user.getId() : null;
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateBy", () -> userId, String.class);
    }
}
