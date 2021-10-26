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

package com.jad.system.modules.system.vo;

import java.util.List;

import lombok.Data;

/**
 * PermCodeVo
 *
 * @author cxxwl96
 * @since 2021/10/26 22:40
 */
@Data
public class PermCodeVo {
    // 超级管理员角色
    private String superRole;

    // 权限列表
    private List<String> codeList;
}
