/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.security.entity.dto;

import com.jad.common.enums.LoginEnum;
import lombok.Data;

/**
 * 验证码登录DTO
 *
 * @author cxxwl96
 * @since 2021/6/27 23:45
 */
@Data
public class LoginWithCaptchaDto {

    // 用户名
    private String username;

    // 密码
    private String password;

    // 登录类型
    private LoginEnum type;

    // 验证码key
    private String codeKey;

    // 验证码value
    private String codeValue;
}
