/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jad.common.base.entity.BaseEntity;
import java.time.LocalDateTime;

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
@ApiModel("系统用户")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLogin;


}
