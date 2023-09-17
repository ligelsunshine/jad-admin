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

package com.jad.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jad.common.entity.User;
import com.jad.common.service.UserService;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

/**
 * Mybatis字段自动填充
 *
 * @author cxxwl96
 * @since 2021/6/28 22:45
 */
@Slf4j
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    private UserService userService;

    @Override
    public void insertFill(MetaObject metaObject) {
        User user = null;
        try {
            user = userService.getCurrentAuthUser();
        } catch (Exception exception) {
            log.warn("Warn inserting. {}", exception.getMessage());
        }
        String userId = user != null ? user.getId() : null;
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "createBy", () -> userId, String.class);

        this.strictInsertFill(metaObject, "enable", () -> true, Boolean.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        User user = null;
        try {
            user = userService.getCurrentAuthUser();
        } catch (Exception exception) {
            log.warn("Warn updating. {}", exception.getMessage());
        }
        String userId = user != null ? user.getId() : null;
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updateBy", () -> userId, String.class);
    }
}
