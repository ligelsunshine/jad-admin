/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxxwl96
 * @since 2021-06-29
 */
@ApiModel("数据表字段")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("form_field")
public class Field extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表单id
     */
    private String tableId;

    /**
     * 中文名称
     */
    private String name;

    /**
     * 是否为主属性
     */
    private Boolean isMain;

    /**
     * 实体属性名称
     */
    private String attributeName;

    /**
     * 实体属性数据类型（基本数据类型）
     */
    private String attributeDataType;

    /**
     * 表单字段名称
     */
    private String fieldName;

    /**
     * 表单字段数据类型（数据库数据类型）
     */
    private String fieldDataType;

    /**
     * 表单字段长度
     */
    private Integer fieldLength;

    /**
     * 表单字段精确小数
     */
    private Integer fieldDecimalPoint;

    /**
     * 表单字段是否不为空
     */
    private Boolean fieldNotNull;

    /**
     * 表单字段默认值
     */
    private String fieldDefaultValue;

    /**
     * 排序数
     */
    private Integer sortNum;

    /**
     * 控件类型
     */
    private String controlType;

    /**
     * 标识数据/枚举数据，例如：枚举数据 男=0,女=1
     */
    private String tag;

    /**
     * 控件placeholder
     */
    private String placeholder;

    /**
     * 数据正则表达式验证
     */
    private String regularTxt;

    /**
     * 数据正则表达式错误提示
     */
    private String regularMsg;


}
