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

    // Redis中存放用户认证的jwt
    public static final String SECURITY_USER_AUTHENTICATE_TOKEN_KEY_PREFIX = "security-user-authenticate-token-";

    // Redis中存放用户授权信息的key
    public static final String SECURITY_USER_GRANTED_AUTHORITY = "security-user-granted-authority";

    // Redis中存放用户菜单列表
    public static final String SYSTEM_USER_MENU_LIST = "system-user-menu-list";

    // Redis中存放用户菜单树
    public static final String SYSTEM_USER_MENU_TREE = "system-user-menu-tree";

    // Redis中存放角色
    public static final String SYSTEM_ROLE = "system-role";

    // Redis中存放超级管理员角色
    public static final String SYSTEM_ROLE_ADMINISTRATOR = "administrator";

    // Redis中存放字典
    public static final String SYSTEM_DICT = "system-dict";
}
