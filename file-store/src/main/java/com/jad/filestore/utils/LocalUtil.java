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

package com.jad.filestore.utils;

import com.jad.common.exception.BadRequestException;
import com.jad.common.exception.ExecutionException;
import com.jad.filestore.config.FileStoreConfig;
import com.jad.filestore.enums.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Local工具类
 *
 * @author cxxwl96
 * @since 2021/11/16 17:19
 */
@Slf4j
@Component
public class LocalUtil {

    @Autowired
    private FileStoreConfig config;

    private String url;

    @PostConstruct
    public void init() {
        if (Store.valueOfName(config.getStore()) == Store.LOCAL) {
            url = config.getUrl();
            File file = new File(url);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    log.error("make local store '{}' fail", url);
                    throw new BadRequestException("make local store '%s' fail", url);
                }
                log.info("make local store '{}'", url);
            }
        }
    }

    /**
     * 上传文件
     *
     * @param is 文件流
     * @param path 路径
     */
    public void upload(InputStream is, String path) {
        String localPath = url + File.separator + path;
        FileOutputStream fos = null;
        try {
            if (is == null) {
                throw new ExecutionException("the file stream is null.");
            }
            createFile(localPath);
            fos = new FileOutputStream(localPath);
            byte[] bytes = new byte[1024];
            while (is.read(bytes) != -1) {
                fos.write(bytes);
                fos.flush();
            }
            log.info("upload file to '{}'.", localPath);
        } catch (IOException | ExecutionException e) {
            log.error("upload file to '{}' error: {}", localPath, e);
            throw new BadRequestException("上传失败");
        } finally {
            closeStream(fos);
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 本地路径
     */
    public void upload(MultipartFile file, String path) {
        String localPath = url + File.separator + path;
        try {
            createFile(localPath);
            file.transferTo(new File(localPath));
            log.info("upload file to '{}'.", localPath);
        } catch (IOException | ExecutionException e) {
            log.error("upload file to '{}' error: {}", localPath, e);
            throw new BadRequestException("上传失败");
        }
    }

    /**
     * 删除文件
     *
     * @param path 本地路径
     */
    public void delete(String path) {
        String localPath = url + File.separator + path;
        try {
            final File file = new File(localPath);
            if (!file.exists()) {
                log.warn("delete warn, file '{}' not exist.", localPath);
                return;
                // throw new ExecutionException("file not exist.");
            }
            if (!file.delete()) {
                throw new ExecutionException("delete fail.");
            }
            log.info("delete file '{}' success.", localPath);
        } catch (ExecutionException e) {
            log.error("delete file '{}' error: {}", localPath, e);
            throw new BadRequestException("删除失败");
        }
    }

    /**
     * 下载文件
     *
     * @param path 本地路径
     * @return 文件流
     */
    public InputStream download2Stream(String path) {
        String localPath = url + File.separator + path;
        try {
            File file = new File(localPath);
            if (!file.exists()) {
                throw new ExecutionException("file not exist.");
            }
            log.info("download file '{}'.", localPath);
            return FileUtil.getInputStream(file);
        } catch (ExecutionException e) {
            log.error("download file '{}' error: ", localPath, e);
        }
        return null;
    }

    private void createFile(String path) throws IOException, ExecutionException {
        File file = new File(path);
        if (!file.exists()) {
            FileUtil.mkParentDirs(file);
            if (!file.createNewFile()) {
                throw new ExecutionException("make file '%s' error.", path);
            }
        }
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.error("关闭流失败", e);
                throw new BadRequestException("上传失败");
            }
        }
    }
}

