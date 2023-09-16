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

import com.alibaba.fastjson.JSON;
import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.entity.User;
import com.jad.common.exception.BadRequestException;
import com.jad.common.exception.ExecutionException;
import com.jad.common.exception.UnauthorizedException;
import com.jad.common.lang.Result;
import com.jad.common.service.UserService;
import com.jad.filestore.config.FileStoreConfig;
import com.jad.filestore.dto.DownloadConfig;
import com.jad.filestore.dto.UploadConfig;
import com.jad.filestore.entity.FileStore;
import com.jad.filestore.enums.AccessPolicy;
import com.jad.filestore.enums.Store;
import com.jad.filestore.mapper.FileStoreMapper;
import com.jad.filestore.service.FileStoreService;
import com.jad.filestore.utils.LocalUtil;
import com.jad.filestore.utils.MinIoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象存储服务实现类
 *
 * @author cxxwl96
 * @since 2021/11/20 21:12
 */
@Slf4j
@Service
public class FileStoreServiceImpl extends BaseServiceImpl<FileStoreMapper, FileStore> implements FileStoreService {
    // base64最大限制20M
    private static final int BASE64_MAXIMUM = 20971520;

    @Autowired
    private FileStoreConfig fileStoreConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private MinIoUtil minIoUtil;

    @Autowired
    private LocalUtil localUtil;

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
            log.error("Upload failed. Failed to save database");
            throw new BadRequestException("上传失败");
        }
        // 上传文件
        uploadFile(file, fileStore, uploadConfig);
        return fileStore;
    }

    /**
     * 删除文件
     *
     * @param fileId 文件ID
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean delete(String fileId) {
        final FileStore fileStore = getFileStore(fileId);
        switch (fileStore.getStore()) {
            case LOCAL:
                localUtil.delete(fileStore.getPath());
                break;
            case MINIO:
                minIoUtil.removeObject(fileStore.getPath());
                break;
            case QINIU:
                // TODO QINIU delete
                break;
            case ALIYUN:
                // TODO ALIYUN delete
                break;
            case TENCENTYUN:
                // TODO TENCENTYUN delete
                break;
            default:
                log.error("删除文件失败，该文件未设置任何存储源，fileID: {}", fileStore.getId());
                throw new BadRequestException("删除失败");
        }
        if (!super.removeById(fileId)) {
            throw new BadRequestException("删除失败");
        }
        return true;
    }

    /**
     * 批量删除文件
     *
     * @param ids 文件ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteArr(Collection<String> ids) {
        final ArrayList<String> success = new ArrayList<>();
        final ArrayList<String> failed = new ArrayList<>();
        for (String id : ids) {
            if (this.delete(id)) {
                success.add(id);
            } else {
                failed.add(id);
            }
        }
        if (failed.size() > 0) {
            final String msg = String.format("成功删除%d个，失败%d个", success.size(), failed.size());
            log.info("success: {}, failed: {}", JSON.toJSONString(success), JSON.toJSONString(failed));
            throw new BadRequestException(msg);
        }
        return true;
    }

    /**
     * 获取文件
     *
     * @param fileId 文件ID
     * @return 文件
     */
    @Override
    public FileStore getFileStore(String fileId) {
        final FileStore fileStore = super.getById(fileId);
        if (fileStore == null) {
            throw new BadRequestException("文件不存在. fileId: " + fileId);
        }
        // 如文件为私有文件
        if (fileStore.getAccessPolicy() == AccessPolicy.PRIVATE) {
            // 需要认证
            if (!userService.Authenticated()) {
                log.error("未登录认证，文件的访问策略为PRIVATE需要登录认证，fileID: {}", fileStore.getId());
                throw new UnauthorizedException();
            }
            // 只能文件所有者下载
            final User authUser = userService.getCurrentAuthUser();
            if (!authUser.getId().equalsIgnoreCase(fileStore.getCreateBy())) {
                log.error("文件的访问策略为PRIVATE，只能文件所有者才有权限，fileID: {}，requestUserID: {}",
                    fileStore.getId(), authUser.getId());
                throw new BadRequestException("您没有权限");
            }
        }
        return fileStore;
    }

    /**
     * 分组获取FileStore
     *
     * @param groupId 分组ID
     * @return FileStore
     */
    @Override
    public List<FileStore> getFileStoreByGroup(String groupId) {
        final List<FileStore> list = super.lambdaQuery().eq(FileStore::getGroupId, groupId).list();
        // 获取公有的file
        final List<FileStore> stores = list.stream()
            .filter(item -> item.getAccessPolicy() == AccessPolicy.PUBLIC)
            .collect(Collectors.toList());
        // 获取私有的file
        final List<FileStore> privateStores = list.stream()
            .filter(item -> item.getAccessPolicy() == AccessPolicy.PRIVATE)
            .collect(Collectors.toList());
        // 如文件为私有文件需要认证
        if (privateStores.size() > 0 && userService.Authenticated()) {
            final User authUser = userService.getCurrentAuthUser();
            // 获取文件所有者的文件
            final List<FileStore> authedFileStores = privateStores.stream()
                .filter(item -> authUser.getId().equalsIgnoreCase(item.getCreateBy()))
                .collect(Collectors.toList());
            stores.addAll(authedFileStores);
        }
        return stores;
    }

    /**
     * 文件下载
     *
     * @param config 下载配置
     * @param response 响应
     * @return 下载结果
     */
    @Override
    public Result<?> download(DownloadConfig config, HttpServletResponse response) {
        final FileStore fileStore = getFileStore(config.getFileId());
        // 下载文件
        return downloadFile(fileStore, config, response);
    }

    /**
     * 获取文件流
     *
     * @param fileStore fileStore
     * @return 文件流
     */
    @Override
    public InputStream getFileInputStream(FileStore fileStore) {
        InputStream is = null;
        switch (fileStore.getStore()) {
            case LOCAL:
                is = localUtil.download2Stream(fileStore.getPath());
                break;
            case MINIO:
                is = minIoUtil.download2Stream(fileStore.getPath());
                break;
            case QINIU:
                // TODO QINIU download
                break;
            case ALIYUN:
                // TODO ALIYUN download
                break;
            case TENCENTYUN:
                // TODO TENCENTYUN download
                break;
            default:
                log.error("下载文件失败，该文件未设置任何存储源，fileID: {}", fileStore.getId());
                throw new BadRequestException("下载失败");
        }
        if (is == null) {
            log.error("下载文件失败，获取文件流为null，fileID: {}", fileStore.getId());
            throw new BadRequestException("下载失败").setNeedPrintStackTrace(false);
        }
        return is;
    }

    private Result<?> downloadFile(FileStore fileStore, DownloadConfig config, HttpServletResponse response) {
        final InputStream is = getFileInputStream(fileStore);
        switch (config.getType()) {
            case STREAM:
                transferStream(fileStore, is, response);
                return Result.success("下载成功");
            case BASE64:
                String base64 = "data:" + fileStore.getMemi() + ";base64," + transferBase64(fileStore, is);
                return Result.success("", base64);
            case URL:
                String url = transferUrl(fileStore);
                return Result.success("", url);
            default:
                log.error("下载文件失败，指定下载方式有误，下载方式只能[STREAM,BASE64,URL], fileID: {}",
                    config.getFileId());
                throw new BadRequestException("下载文件失败，指定下载方式有误，下载方式只能[STREAM,BASE64,URL]");
        }
    }

    private void transferStream(FileStore fileStore, InputStream is, HttpServletResponse response) {
        OutputStream os = null;
        try {
            String contentDisposition = String.format("attachment;fileName=%s;filename*=utf-8''%s", fileStore.getName(),
                URLEncoder.encode(fileStore.getName(), "UTF-8"));
            //响应头设置
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setHeader("content-type", fileStore.getMemi());
            response.setContentType(fileStore.getMemi() + ";charset=UTF-8");
            response.setHeader("Content-Disposition", contentDisposition);
            response.addHeader("Access-Control-Allow-Origin", "*"); // 实现跨域
            os = response.getOutputStream();
            byte[] buff = new byte[1024];
            int len;
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            log.error("下载文件异常失败", e);
            throw new BadRequestException("下载文件异常失败");
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                log.error("下载文件异常失败", e);
            }
        }
    }

    private String transferBase64(FileStore fileStore, InputStream is) {
        try {
            if (is.available() > BASE64_MAXIMUM) {
                log.error("BASE64下载最大大小为20M, fileID: {}, fileSize: {}", fileStore.getId(), is.available());
                throw new BadRequestException("BASE64下载最大大小为20M");
            }
            byte[] bytes = IoUtil.readBytes(is);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            log.error("下载文件异常失败", e);
            throw new BadRequestException("下载文件异常失败");
        }
    }

    private String transferUrl(FileStore fileStore) {
        String url = null;
        try {
            switch (fileStore.getStore()) {
                case LOCAL:
                    throw new ExecutionException("不能获取本地文件URL，请使用流方式下载或使用BASE64方式下载");
                case MINIO:
                    url = minIoUtil.getUrl(fileStore.getPath());
                    break;
                case QINIU:
                    // TODO QINIU getUrl
                    break;
                case ALIYUN:
                    // TODO ALIYUN getUrl
                    break;
                case TENCENTYUN:
                    // TODO TENCENTYUN getUrl
                    break;
                default:
                    log.error("获取文件URL失败，该文件未设置任何存储源，fileID: {}", fileStore.getId());
                    throw new BadRequestException("获取URL失败");
            }
        } catch (ExecutionException e) {
            log.error(e.getMessage(), e);
        }
        return url;
    }

    private void uploadFile(MultipartFile file, FileStore fileStore, UploadConfig uploadConfig) throws IOException {
        // 如果访问策略是私有的，则上传需登录认证
        if (uploadConfig.getAccessPolicy() == AccessPolicy.PRIVATE && !userService.Authenticated()) {
            log.error("未登录认证，上传文件的访问策略为PRIVATE需要登录认证");
            throw new UnauthorizedException();
        }
        final Store store = Store.valueOfName(fileStoreConfig.getStore());
        String path = fileStore.getPath();
        switch (store) {
            case LOCAL:
                localUtil.upload(file, path);
                break;
            case MINIO:
                minIoUtil.upload(file.getInputStream(), path);
                break;
            case QINIU:
                // TODO QINIU upload
                break;
            case ALIYUN:
                // TODO ALIYUN upload
                break;
            case TENCENTYUN:
                // TODO TENCENTYUN upload
                break;
            default:
                log.error("上传文件失败，请设置系统对象存储源");
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
            fileStore.setGroupId(groupId);
        } else {
            fileStore.setGroupId(IdUtil.nanoId());
        }
        final InputStream inputStream = file.getInputStream();
        String suffix = "";
        if (filename.contains(".")) {
            suffix = filename.substring(filename.lastIndexOf("."));
        }
        String path = getPath(uploadConfig, suffix);
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