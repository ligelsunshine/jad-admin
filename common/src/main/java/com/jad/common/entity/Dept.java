package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.TreeNode;
import com.jad.common.enums.Status;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel("部门")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
public class Dept extends TreeNode<Dept> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门名称")
    @NotEmpty(message = "部门名称不能为空")
    private String name;

    @ApiModelProperty(value = "状态【启用：0,停用：1】")
    private Status status = Status.ENABLE;

}
