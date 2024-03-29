/*
 * Copyright (c) 2021-2023, jad (cxxwl96@sina.com).
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

package com.jad.common.service;

import com.jad.common.base.service.TreeService;
import com.jad.common.entity.Settings;

import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 系统设置管理服务接口类
 *
 * @author cxxwl96
 * @since 2023/08/28 22:24
 */
public interface SettingsService extends TreeService<Settings> {
    /**
     * 绑定菜单
     *
     * @param menuId 菜单ID
     */
    void bindMenu(String menuId);

    /**
     * 获取用户设置树
     *
     * @return 用户设置树
     */
    List<Settings> getUserSettingTree();

    /**
     * 清除用户缓存的设置树
     */
    void clearUserSettingTree();

    /**
     * 清除用户缓存的设置树
     *
     * @param username 用户名
     */
    void clearUserSettingTree(@Nullable String username);
}
