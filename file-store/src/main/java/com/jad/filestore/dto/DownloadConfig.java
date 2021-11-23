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

import com.jad.filestore.enums.DownloadType;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * DownloadConfig
 *
 * @author cxxwl96
 * @since 2021/11/22 23:18
 */
@Data
public class DownloadConfig {
    @NotEmpty(message = "文件ID不能为空")
    private String fileId;

    private DownloadType type = DownloadType.STREAM;
}
