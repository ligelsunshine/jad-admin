/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类
 *
 * @author cxxwl96
 * @since 2021/6/18 22:07
 */
@ApiModel(value = "实体基类")
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", insertStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time", updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "是否启用【未启用：0, 已启用：1（默认）】")
    @TableField(value = "enable", updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT)
    @TableLogic(value = "1", delval = "0")
    private Boolean enable;

}
