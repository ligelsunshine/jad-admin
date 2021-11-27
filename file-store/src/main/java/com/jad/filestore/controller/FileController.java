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

package com.jad.filestore.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.lang.Result;
import com.jad.filestore.dto.DownloadConfig;
import com.jad.filestore.dto.UploadConfig;
import com.jad.filestore.entity.FileStore;
import com.jad.filestore.enums.DownloadType;
import com.jad.filestore.service.FileStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 对象存储相关接口
 *
 * @author cxxwl96
 * @since 2021/11/20 21:12
 */
@Api(tags = "公共接口 - 对象存储相关接口")
@RestController
@RequestMapping("/sys/file")
public class FileController extends BaseController {
    @Autowired
    private FileStoreService service;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result upload(MultipartFile file, UploadConfig config) {
        final FileStore fileStore = service.upload(file, config);
        return Result.success("上传成功", fileStore);
    }

    @ApiOperation("删除文件")
    @PostMapping("/delete/{fileId}")
    public Result delete(@PathVariable String fileId) {
        if (!service.delete(fileId)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("下载文件")
    @GetMapping("/download/{fileId}")
    public void download(@PathVariable String fileId, HttpServletResponse response) {
        final DownloadConfig config = new DownloadConfig(fileId, DownloadType.STREAM);
        service.download(config, response);
    }

    @ApiOperation("获取文件BASE64")
    @GetMapping("/base64/{fileId}")
    public Result base64(@PathVariable String fileId) {
        final DownloadConfig config = new DownloadConfig(fileId, DownloadType.BASE64);
        return service.download(config, response);
    }

    @ApiOperation("获取文件URL")
    @GetMapping("/url/{fileId}")
    public Result url(@PathVariable String fileId) {
        final DownloadConfig config = new DownloadConfig(fileId, DownloadType.URL);
        return service.download(config, response);
    }
}