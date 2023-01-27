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

package com.jad.filestore.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.jad.common.exception.BadRequestException;

/**
 * 存储源
 * - 本地存储:local
 * - minio存储:minio
 * - 七牛云:qiniu
 * - 阿里云:aliyun
 * - 腾讯云:tencent
 *
 * @author cxxwl96
 * @since 2021/11/20 21:12
 */
public enum Store {
    LOCAL(0),
    MINIO(1),
    QINIU(2),
    ALIYUN(3),
    TENCENTYUN(4);

    @EnumValue
    @JsonValue
    private final int index;

    Store(int index) {
        this.index = index;
    }

    public static Store valueOfName(String store) {
        for (Store value : Store.values()) {
            if (value.name().equalsIgnoreCase(store)) {
                return value;
            }
        }
        throw new BadRequestException("找不到存储源: %s", store);
    }

    public int getIndex() {
        return index;
    }
}
