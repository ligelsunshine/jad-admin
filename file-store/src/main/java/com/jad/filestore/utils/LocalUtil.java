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

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.hutool.core.io.FileUtil;
import lombok.extern.log4j.Log4j2;

/**
 * LocalUtil
 *
 * @author cxxwl96
 * @since 2021/11/25 23:01
 */
@Log4j2
@Component
public class LocalUtil {
    /**
     * 上传文件
     *
     * @param is 文件流
     * @param path 路径
     */
    public void upload(InputStream is, String path) {
        FileOutputStream fos = null;
        try {
            if (is == null) {
                log.error("上传失败,文件流为null");
                throw new RuntimeException("上传失败");
            }
            createFile(path);
            fos = new FileOutputStream(path);
            byte[] bytes = new byte[1024];
            while (is.read(bytes) != -1) {
                fos.write(bytes);
                fos.flush();
            }
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException("上传失败");
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
        try {
            final File localFile = new File(path);
            createFile(path);
            file.transferTo(localFile);
        } catch (IOException e) {
            log.error("上传本地文件失败: {}", path, e);
            throw new BadRequestException("上传失败");
        }
    }

    private void createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            FileUtil.mkParentDirs(file);
            if (!file.createNewFile()) {
                log.error("上传失败,创建文件失败,path:{}", path);
                throw new RuntimeException("上传失败");
            }
        }
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.error(e);
                throw new RuntimeException("上传失败");
            }
        }
    }
}