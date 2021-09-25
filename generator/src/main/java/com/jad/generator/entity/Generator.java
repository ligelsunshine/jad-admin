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

package com.jad.generator.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import com.jad.generator.model.Model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 代码生成实体
 *
 * @author cxxwl96
 * @since 2021/09/18 21:58
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("devtools_generate")
public class Generator extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据源")
    private String ds;

    @ApiModelProperty(value = "module")
    private String module;

    @ApiModelProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "namespace")
    private String namespace;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "mainField")
    private String mainField;

    @ApiModelProperty(value = "treeModel")
    private boolean treeModel;

    @ApiModelProperty(value = "logic")
    private boolean logic;

    @ApiModelProperty(value = "模型")
    @TableField(value = "model")
    private String modelJson;

    @ApiModelProperty(value = "模型")
    @TableField(exist = false)
    private Model model;

    public Model getModel() {
        return JSON.parseObject(this.modelJson, Model.class);
    }

    public void setModel(Model model) {
        this.model = model;
        this.modelJson = JSON.toJSONString(model);
    }
}
