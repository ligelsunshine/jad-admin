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

package com.jad.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

import lombok.Getter;

/**
 * 菜单类型 [0：目录 1：菜单 2：按钮]
 *
 * @author cxxwl96
 * @since 2021/7/20 23:49
 */
public enum MenuType {
    // 目录
    DIRECTORY(0),
    // 菜单
    MENU(1),
    // 按钮
    BUTTON(2);

    @EnumValue
    @JsonValue
    @Getter
    private final int index;

    MenuType(int index) {
        this.index = index;
    }

    /**
     * 获取菜单类型
     *
     * @param index index
     * @return 菜单类型
     */
    public static Optional<MenuType> getMenuType(int index) {
        for (MenuType menuType : MenuType.values()) {
            if (menuType.getIndex() == index) {
                return Optional.of(menuType);
            }
        }
        return Optional.empty();
    }
}
