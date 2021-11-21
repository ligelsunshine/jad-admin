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

package com.jad.filestore.service.impl;

import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.exception.BadRequestException;
import com.jad.common.exception.UnauthorizedException;
import com.jad.common.service.UserService;
import com.jad.filestore.config.FileStoreConfig;
import com.jad.filestore.dto.UploadConfig;
import com.jad.filestore.entity.FileStore;
import com.jad.filestore.enums.AccessPolicy;
import com.jad.filestore.enums.Store;
import com.jad.filestore.mapper.FileStoreMapper;
import com.jad.filestore.service.FileStoreService;
import com.jad.filestore.utils.MinIoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

/**
 * 对象存储服务实现类
 *
 * @author cxxwl96
 * @since 2021/11/20 21:12
 */
@Log4j2
@Service
public class FileStoreServiceImpl extends BaseServiceImpl<FileStoreMapper, FileStore> implements FileStoreService {
    @Autowired
    private FileStoreConfig fileStoreConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private MinIoUtil minIoUtil;

    /**
     * 文件上传
     *
     * @param file 文件
     * @param uploadConfig 上传配置
     * @return 文件信息
     */
    @Override
    @SneakyThrows
    @Transactional
    public FileStore upload(MultipartFile file, UploadConfig uploadConfig) {
        final FileStore fileStore = createStore(file, uploadConfig);
        // 保存数据库
        if (!super.save(fileStore)) {
            log.error("上传失败，保存数据库失败");
            throw new BadRequestException("上传失败");
        }
        // 上传文件
        uploadFile(file, fileStore, uploadConfig);
        return fileStore;
    }

    private void uploadFile(MultipartFile file, FileStore fileStore, UploadConfig uploadConfig) throws IOException {
        // 如果访问策略是私有的，则上传需登录认证
        if (uploadConfig.getAccessPolicy() == AccessPolicy.PRIVATE && !userService.Authenticated()) {
            log.error("未登录认证，上传文件的访问策略为PRIVATE需要登录认证");
            throw new UnauthorizedException();
        }
        final Store store = Store.valueOfName(fileStoreConfig.getStore());
        String path = fileStore.getPath();
        if (store == Store.LOCAL) {
            final File localFile = new File(fileStoreConfig.getUrl() + File.separator + path);
            if (!FileUtil.exist(path) && !new File(path).mkdirs()) {
                log.error("创建文件失败: {}", path);
                throw new BadRequestException("创建文件失败");
            }
            file.transferTo(localFile);
            return;
        }
        if (store == Store.MINIO) {
            minIoUtil.upload(file.getInputStream(), path);
        } else {
            log.error("上传文件失败，请设置对象存储源");
            throw new BadRequestException("上传失败");
        }
    }

    private FileStore createStore(MultipartFile file, UploadConfig uploadConfig) throws IOException {
        final String groupId = uploadConfig.getGroupId();
        if (file.isEmpty()) {
            throw new BadRequestException("上传文件不能为空");
        }
        final String filename = file.getOriginalFilename();
        if (StrUtil.isBlank(filename)) {
            throw new BadRequestException("无效的文件名");
        }
        final FileStore fileStore = new FileStore();
        if (StrUtil.isNotBlank(groupId)) {
            final Long count = super.lambdaQuery().eq(FileStore::getGroupId, groupId).count();
            if (count == 0) {
                log.error("GroupID '{}' 不存在", groupId);
                throw new BadRequestException("GroupID不存在");
            }
            fileStore.setGroupId(groupId);
        } else {
            fileStore.setGroupId(IdUtil.fastSimpleUUID());
        }
        final InputStream inputStream = file.getInputStream();
        final String suffix = filename.substring(filename.lastIndexOf("."));
        final String path = getPath(uploadConfig, suffix);
        fileStore.setName(filename);
        fileStore.setType(suffix);
        fileStore.setSize(file.getSize());
        fileStore.setPath(path);
        fileStore.setMd5(SecureUtil.md5(inputStream));
        fileStore.setMemi(file.getContentType());
        fileStore.setAccessPolicy(uploadConfig.getAccessPolicy());
        fileStore.setStore(Store.valueOfName(fileStoreConfig.getStore()));
        return fileStore;
    }

    private String getPath(UploadConfig uploadConfig, String suffix) {
        final String fileRoot = uploadConfig.getUploadType().name().toLowerCase(Locale.ROOT);
        final String dateFormat = DateUtil.format(new Date(), "yyyy-MM-dd");
        final String filename = IdUtil.objectId() + suffix;
        return String.format(Locale.ROOT, "/%s/%s/%s", fileRoot, dateFormat, filename);
    }
}
