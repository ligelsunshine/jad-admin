/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.enums;

/**
 * 登录类型枚举
 *
 * @author cxxwl96
 * @since 2021/6/28 00:13
 */
public enum LoginEnum {
    // 仅密码
    PASSWORD("仅密码"),
    // 密码和图片验证码
    PASSWORD_CAPTCHA("密码和图片验证码"),
    // 密码和短信验证码
    PASSWORD_SMS_CODE("密码和短信验证码"),
    // 密码和邮箱验证码
    PASSWORD_EMAIL_CODE("密码和邮箱验证码"),
    // 仅短信验证码
    SMS_CODE("仅短信验证码");

    private final String des;

    LoginEnum(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }
}
