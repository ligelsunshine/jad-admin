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

package com.jad.filestore.dto;

import com.jad.filestore.enums.AccessPolicy;
import com.jad.filestore.enums.UploadType;

import lombok.Data;

/**
 * UploadConfig
 *
 * @author cxxwl96
 * @since 2021/11/21 20:09
 */
@Data
public class UploadConfig {
    // 文件分组ID
    private String groupId;

    // 上传类型
    private UploadType uploadType = UploadType.OTHER;

    // 访问策略
    private AccessPolicy accessPolicy = AccessPolicy.PUBLIC;
}
