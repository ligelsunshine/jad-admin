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

package com.jad.filestore.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import com.jad.filestore.enums.AccessPolicy;
import com.jad.filestore.enums.Store;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 对象存储实体
 *
 * @author cxxwl96
 * @since 2021/11/20 21:12
 */
@ApiModel("对象存储实体")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_file_store")
public class FileStore extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分组")
    private String groupId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "大小")
    private long size;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "MD5")
    private String md5;

    @ApiModelProperty(value = "MEMI类型")
    private String memi;

    @ApiModelProperty(value = "访问策略")
    private AccessPolicy accessPolicy;

    @ApiModelProperty(value = "存储源")
    private Store store;

}
