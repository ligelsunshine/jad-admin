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

package com.jad.common.service.impl;

import com.jad.common.base.service.impl.TreeServiceImpl;
import com.jad.common.entity.Menu;
import com.jad.common.entity.Settings;
import com.jad.common.exception.BadRequestException;
import com.jad.common.mapper.SettingsMapper;
import com.jad.common.service.MenuService;
import com.jad.common.service.SettingsService;
import com.jad.common.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;

/**
 * 系统设置管理服务实现类
 *
 * @author cxxwl96
 * @since 2023/08/28 22:24
 */
@Service
public class SettingsServiceImpl extends TreeServiceImpl<SettingsMapper, Settings> implements SettingsService {
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    /**
     * 绑定菜单
     *
     * @param menuId 菜单ID
     */
    @Override
    public void bindMenu(String menuId) {
        // 1、检查菜单合法性
        Menu menu = menuService.getById(menuId);
        if (menu == null) {
            throw new BadRequestException("您绑定的菜单不存在");
        }
        if (StrUtil.isBlank(menu.getCode())) {
            throw new BadRequestException("您绑定的菜单编码为空");
        }
        // 2、检查菜单是否为空菜单，即是否有子菜单，不能绑定非空菜单
        Long count = menuService.lambdaQuery().eq(Menu::getPId, menuId).count();
        if (count > 0) {
            throw new BadRequestException("绑定的菜单不能拥有子菜单，请重新选择");
        }
        // 3、检查是否已绑定
        if (this.exist(menuId)) {
            throw new BadRequestException("该菜单已被绑定");
        }
        // 4、添加设置根节点
        Settings settings = new Settings();
        settings.setId(menuId);
        settings.setTitle("设置根节点");
        settings.setCode(menu.getCode());
        if (!this.save(settings)) {
            throw new BadRequestException("绑定失败");
        }
    }
}
