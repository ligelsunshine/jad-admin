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

package com.jad.filestore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * FileStoreConfig
 *
 * @author cxxwl96
 * @since 2021/11/18 22:08
 */
@Data
@Configuration
public class FileStoreConfig {

    @Value("${file-store.store}")
    private String store;

    @Value("${file-store.url}")
    private String url = "";

    @Value("${file-store.accessKey}")
    private String accessKey = "";

    @Value("${file-store.secretKey}")
    private String secretKey = "";

    @Value("${file-store.bucket}")
    public String bucket = "";
}
