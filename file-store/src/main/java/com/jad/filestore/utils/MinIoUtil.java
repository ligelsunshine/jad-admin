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
import com.jad.filestore.config.FileStoreConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import cn.hutool.core.collection.CollUtil;
import io.minio.BucketExistsArgs;
import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import lombok.extern.log4j.Log4j2;

/**
 * MinIo工具类
 *
 * @author cxxwl96
 * @since 2021/11/16 17:19
 */
@Log4j2
@Component
public class MinIoUtil {

    @Autowired
    private FileStoreConfig config;

    private MinioClient client;

    @PostConstruct
    public void init() {
        client = MinioClient.builder()
            .endpoint(config.getUrl())
            .credentials(config.getAccessKey(), config.getSecretKey())
            .build();
    }

    /**
     * 创建文件桶
     *
     * @param bucket 文件桶
     */
    public void makeBucket(String bucket) {
        try {
            if (!bucketExist(bucket)) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
                log.info("make bucket '{}'.", bucket);
            }
            log.warn("make bucket '{}' error, it it already exists.", bucket);
        } catch (Exception e) {
            log.error("make bucket '{}' error: {}", bucket, e.getMessage());
        }
    }

    /**
     * 刪除文件桶
     *
     * @param bucket 文件桶
     */
    public void removeBucket(String bucket) {
        try {
            if (bucketExist(bucket)) {
                client.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
                log.info("remove bucket '{}'.", bucket);
                return;
            }
            log.warn("remove bucket '{}' error, it doesn't  exists.", bucket);
        } catch (Exception e) {
            log.error("remove bucket '{}' error: {}", bucket, e.getMessage());
        }
    }

    /**
     * 文件桶是否存在
     *
     * @param bucket 文件桶
     * @return 是否存在
     */
    public boolean bucketExist(String bucket) {
        try {
            return client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            log.error("bucket '{}' exist error: {}", bucket, e.getMessage());
        }
        return true;
    }

    /**
     * 获取文件桶列表
     *
     * @return 文件桶列表
     */
    public List<Bucket> listBuckets() {
        try {
            return client.listBuckets();
        } catch (Exception e) {
            log.error("get bucket list error: {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * 上传文件
     *
     * @param filename 文件名，本地文件全路径
     * @param object 对象，云端文件全路径
     */
    public void upload(String filename, String object) {
        upload(config.getBucket(), filename, object);
    }

    /**
     * 上传文件
     *
     * @param bucket 文件桶
     * @param filename 文件名，本地文件全路径
     * @param object 对象，云端文件全路径
     */
    public void upload(String bucket, String filename, String object) {
        try {
            UploadObjectArgs args = UploadObjectArgs.builder().bucket(bucket).object(object).filename(filename).build();
            client.uploadObject(args);
            log.info("upload file '{}' to '{}'.", filename, object);
        } catch (Exception e) {
            log.error("upload file to '{}' error: {}", object, e.getMessage());
            throw new BadRequestException("上传失败");
        }
    }

    /**
     * 上传文件
     *
     * @param is 文件流
     * @param object 对象，云端文件全路径
     */
    public void upload(InputStream is, String object) {
        upload(config.getBucket(), is, object);
    }

    /**
     * 上传文件
     *
     * @param bucket 文件桶
     * @param is 文件流
     * @param object 对象，云端文件全路径
     */
    public void upload(String bucket, InputStream is, String object) {
        try {
            PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucket)
                .stream(is, is.available(), -1)
                .object(object)
                .build();
            client.putObject(args);
            log.info("upload file to '{}'.", object);
        } catch (Exception e) {
            log.error("upload file to '{}' error: {}", object, e.getMessage());
            throw new BadRequestException("上传失败");
        }
    }

    /**
     * 删除文件
     *
     * @param object 对象，云端文件全路径
     */
    public void removeObject(String object) {
        removeObject(config.getBucket(), object);
    }

    /**
     * 删除文件
     *
     * @param bucket 文件桶
     * @param object 对象，云端文件全路径
     */
    public void removeObject(String bucket, String object) {
        try {
            RemoveObjectArgs args = RemoveObjectArgs.builder().bucket(bucket).object(object).build();
            client.removeObject(args);
            log.info("remove file '{}'.", object);
        } catch (Exception e) {
            log.error("remove file '{}' error: {}", object, e.getMessage());
            throw new BadRequestException("上传失败");
        }
    }

    /**
     * 删除文件
     *
     * @param collection 对象，云端文件全路径
     */
    public void removeObjects(Collection<String> collection) {
        removeObjects(config.getBucket(), collection);
    }

    /**
     * 删除文件
     *
     * @param bucket 文件桶
     * @param collection 对象，云端文件全路径
     */
    public void removeObjects(String bucket, Collection<String> collection) {
        try {
            if (CollUtil.isEmpty(collection)) {
                log.warn("remove multiple files with empty objects");
                return;
            }
            List<DeleteObject> objects = new LinkedList<>();
            collection.forEach(item -> objects.add(new DeleteObject(item)));
            RemoveObjectsArgs args = RemoveObjectsArgs.builder().bucket(bucket).objects(objects).build();
            Iterable<Result<DeleteError>> results = client.removeObjects(args);
            if (results != null) {
                for (Result<DeleteError> result : results) {
                    DeleteError error = result.get();
                    log.error("remove file '{}' error: {}.", error.objectName(), error.message());
                }
            }
        } catch (Exception e) {
            log.error("remove multiple files error: {}", e.getMessage());
            throw new BadRequestException("删除失败");
        }
    }

    /**
     * 下载文件
     *
     * @param filename 文件名，本地文件全路径
     * @param object 对象，云端文件全路径
     */
    public void download2Local(String filename, String object) {
        download2Local(config.getBucket(), filename, object);
    }

    /**
     * 下载文件
     *
     * @param bucket 文件桶
     * @param filename 文件名，本地文件全路径
     * @param object 对象，云端文件全路径
     */
    public void download2Local(String bucket, String filename, String object) {
        try {
            DownloadObjectArgs args = DownloadObjectArgs.builder()
                .bucket(bucket)
                .object(object)
                .filename(filename)
                .build();
            client.downloadObject(args);
            log.info("download file '{}' to '{}'.", object, filename);
        } catch (Exception e) {
            log.error("download file '{}' error: {}", object, e.getMessage());
        }
    }

    /**
     * 下载文件
     *
     * @param object 对象，云端文件全路径
     * @return 输入流
     */
    public InputStream download2Stream(String object) {
        return download2Stream(config.getBucket(), object);
    }

    /**
     * 下载文件
     *
     * @param bucket 文件桶
     * @param object 对象，云端文件全路径
     * @return 输入流
     */
    public InputStream download2Stream(String bucket, String object) {
        try {
            GetObjectArgs args = GetObjectArgs.builder().bucket(bucket).object(object).build();
            InputStream stream = client.getObject(args);
            log.info("download file '{}'.", object);
            return stream;
        } catch (Exception e) {
            log.error("download file '{}' error: {}", object, e.getMessage());
        }
        return null;
    }

    /**
     * 复制文件
     *
     * @param sourceBucket 源文件桶
     * @param sourceObject 源对象
     * @param targetBucket 目标文件桶
     * @param targetObject 目标对象
     * @return 是否复制成功
     */
    public boolean copy(String sourceBucket, String sourceObject, String targetBucket, String targetObject) {
        try {
            CopySource source = CopySource.builder().bucket(sourceBucket).object(sourceObject).build();
            CopyObjectArgs args = CopyObjectArgs.builder()
                .bucket(targetBucket)
                .object(targetObject)
                .source(source)
                .build();
            client.copyObject(args);
            return true;
        } catch (Exception e) {
            log.error("copy file [{} {}] to [{} {}] error: {}", sourceBucket, sourceObject, targetBucket, targetObject,
                e.getMessage());
        }
        return false;
    }

    /**
     * 获取文件元数据
     *
     * @param object 对象，云端文件全路径
     * @return 文件元数据
     */
    public StatObjectResponse statObject(String object) {
        return statObject(config.getBucket(), object);
    }

    /**
     * 获取文件元数据
     *
     * @param bucket 文件桶
     * @param object 对象，云端文件全路径
     * @return 文件元数据
     */
    public StatObjectResponse statObject(String bucket, String object) {
        try {
            StatObjectArgs args = StatObjectArgs.builder().bucket(bucket).object(object).build();
            return client.statObject(args);
        } catch (Exception e) {
            log.error("get file stat error: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取文件链接
     *
     * @param object 对象，云端文件全路径
     * @return 文件链接
     */
    public String getUrl(String object) {
        return getUrl(config.getBucket(), object, 60 * 60 * 24);
    }

    /**
     * 获取文件链接
     *
     * @param object 对象，云端文件全路径
     * @param expire 过期时间，单位：秒
     * @return 文件链接
     */
    public String getUrl(String object, int expire) {
        return getUrl(config.getBucket(), object, expire);
    }

    /**
     * 获取文件链接
     *
     * @param bucket 文件桶
     * @param object 对象，云端文件全路径
     * @param expire 过期时间，单位：秒
     * @return 文件链接
     */
    public String getUrl(String bucket, String object, int expire) {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucket)
                .object(object)
                .expiry(expire)
                .build();
            String url = client.getPresignedObjectUrl(args);
            log.info("get url '{}'.", url);
            return url;
        } catch (Exception e) {
            log.error("get url error: {}", e.getMessage());
        }
        return "";
    }
}