/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.constant;

/**
 * Redis操作使用到的常量
 *
 * @author cxxwl96
 * @since 2021/6/20 12:56
 */
public class RedisConst {

    // Redis中存放验证码的key
    public static final String SECURITY_LOGIN_CAPTCHA_KEY_PREFIX = "security-login-captcha-";

    // Redis中存放用户授权信息的key
    public static final String SECURITY_USER_GRANTED_AUTHORITY = "security-user-granted-authority";

    // Redis中存放用户认证的jwt
    public static final String SECURITY_USER_AUTHENTICATE_TOKEN_KEY_PREFIX = "security-user-authenticate-token-";

    // Redis中存放菜单
    public static final String SYSTEM_MENU_LIST = "system-menu-list";
}
