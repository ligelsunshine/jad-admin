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
import com.jad.common.enums.MenuType;
import com.jad.common.enums.SettingType;
import com.jad.common.exception.BadRequestException;
import com.jad.common.mapper.SettingsMapper;
import com.jad.common.service.MenuService;
import com.jad.common.service.SettingsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;

/**
 * 系统设置管理服务实现类
 *
 * @author cxxwl96
 * @since 2023/08/28 22:24
 */
@Service
public class SettingsServiceImpl extends TreeServiceImpl<SettingsMapper, Settings> implements SettingsService {
    @Autowired
    private MenuService menuService;

    /**
     * 添加设置
     *
     * @param entity 实体对象
     * @return 是否添加成功
     */
    @Override
    @Transactional
    public boolean save(Settings entity) {
        validateSettings(entity);
        boolean saveSetting = super.save(entity);
        Menu menu = createMenuBySetting(entity);
        boolean saveMenu = menuService.save(menu);
        if (!saveSetting || !saveMenu) {
            throw new BadRequestException("添加设置或菜单失败");
        }
        return true;
    }

    /**
     * 删除子树
     *
     * @param id 子树根节点id
     * @param includeSelf 是否包含子树根节点
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean removeTree(String id, boolean includeSelf) {
        if (!menuService.removeTree(id, includeSelf) || !super.removeTree(id, includeSelf)) {
            throw new BadRequestException("删除设置或菜单失败");
        }
        return true;
    }

    /**
     * 修改设置
     *
     * @param entity 实体对象
     * @return 是否修改成功
     */
    @Override
    @Transactional
    public boolean updateById(Settings entity) {
        Assert.isTrue(super.exist(entity.getId()), () -> new BadRequestException("修改失败，数据不存在"));
        validateSettings(entity);
        boolean updateSetting = super.updateById(entity);
        Menu menu = createMenuBySetting(entity);
        boolean updateMenu = menuService.updateById(menu);
        if (!updateSetting || !updateMenu) {
            throw new BadRequestException("修改设置或菜单失败");
        }
        return true;
    }

    /**
     * 绑定菜单
     *
     * @param menuId 菜单ID
     */
    @Override
    @Transactional
    public void bindMenu(String menuId) {
        // 1、检查菜单合法性
        Menu menu = menuService.getById(menuId);
        Assert.notNull(menu, () -> new BadRequestException("您绑定的菜单不存在"));
        Assert.notBlank(menu.getCode(), () -> new BadRequestException("您绑定的菜单编码为空"));
        // 2、检查菜单是否为空菜单，即是否有子菜单，不能绑定非空菜单
        Long count = menuService.lambdaQuery().eq(Menu::getPId, menuId).count();
        Assert.isTrue(count <= 0, () -> new BadRequestException("绑定的菜单不能拥有子菜单，请重新选择"));
        // 3、清空设置与菜单
        if (super.count() > 0) {
            // 清空设置
            List<String> ids = super.lambdaQuery()
                .select(Settings::getId)
                .list()
                .stream()
                .map(Settings::getId)
                .collect(Collectors.toList());
            if (ids.size() > 0) {
                super.removeByIds(ids);
            }
            // 清空菜单
            ids.remove(menuId); // 不删除菜单中设置的根菜单
            if (ids.size() > 0) {
                menuService.removeByIds(ids);
            }
        }
        // 4、添加设置根节点
        Settings settings = new Settings();
        settings.setId(menuId);
        settings.setTitle("设置根节点");
        settings.setCode(menu.getCode());
        settings.setOrigin(true);
        settings.setSettingType(SettingType.DIRECTORY);
        if (!super.save(settings)) {
            throw new BadRequestException("绑定失败");
        }
    }

    /**
     * 创建菜单实体，设置字段同步菜单字段
     *
     * @param entity 设置实体
     * @return 菜单实体
     */
    private Menu createMenuBySetting(Settings entity) {
        Menu menu = new Menu();
        BeanUtil.copyProperties(entity, menu);
        // 设置菜单不能为空字段
        MenuType menuType = MenuType.getMenuType(entity.getSettingType().getIndex())
            .orElseThrow(() -> new BadRequestException("设置类型错误"));
        menu.setType(menuType);
        menu.setTitle(entity.getTitle());
        menu.setComponent(null);
        if (menuType == MenuType.DIRECTORY || menuType == MenuType.MENU) {
            menu.setPath("path" + entity.getId());
        }
        return menu;
    }

    /**
     * 添加或修改时校验设置字段
     *
     * @param entity 设置实体
     */
    private void validateSettings(Settings entity) {
        // 不允许修改跟节点
        Assert.isTrue(!entity.isOrigin(), () -> new BadRequestException("不允许变更根节点"));
        // 必须要有父级节点
        Assert.notBlank(entity.getPId(), () -> new BadRequestException("必须添加父级节点"));
        Settings parentEntity = super.getById(entity.getPId());
        Assert.notNull(parentEntity, () -> new BadRequestException("必须添加父级节点"));
        // 目录下面只能添加目录、设置页
        if (parentEntity.getSettingType() == SettingType.DIRECTORY) {
            if (entity.getSettingType() != SettingType.DIRECTORY && entity.getSettingType() != SettingType.PAGE) {
                throw new BadRequestException("\"目录\"下面只能添加\"目录\"、\"设置页\"");
            }
        } else if (parentEntity.getSettingType() == SettingType.PAGE) {
            if (entity.getSettingType() != SettingType.ITEM) {
                throw new BadRequestException("\"设置页\"下面只能添加\"设置项\"");
            }
        } else {
            throw new BadRequestException("\"设置项\"不能再添加子设置了");
        }
    }
}
