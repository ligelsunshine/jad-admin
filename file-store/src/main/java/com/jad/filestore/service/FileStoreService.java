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

package com.jad.filestore.service;

import com.jad.common.base.service.BaseService;
import com.jad.common.lang.Result;
import com.jad.filestore.dto.DownloadConfig;
import com.jad.filestore.dto.UploadConfig;
import com.jad.filestore.entity.FileStore;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 对象存储服务接口类
 *
 * @author cxxwl96
 * @since 2021/11/20 21:12
 */
public interface FileStoreService extends BaseService<FileStore> {
    /**
     * 文件上传
     *
     * @param file 文件
     * @param uploadConfig 上传配置
     * @return 文件信息
     */
    FileStore upload(MultipartFile file, UploadConfig uploadConfig);

    /**
     * 文件下载
     *
     * @param config 下载配置
     * @param response 响应
     * @return 下载结果
     */
    Result download(DownloadConfig config, HttpServletResponse response);
}