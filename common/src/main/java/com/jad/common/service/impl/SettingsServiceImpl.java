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
import com.jad.common.entity.Settings;
import com.jad.common.mapper.SettingsMapper;
import com.jad.common.service.SettingsService;

import org.springframework.stereotype.Service;

/**
 * 系统设置管理服务实现类
 *
 * @author cxxwl96
 * @since 2023/08/28 22:24
 */
@Service
public class SettingsServiceImpl extends TreeServiceImpl<SettingsMapper, Settings> implements SettingsService {

}
