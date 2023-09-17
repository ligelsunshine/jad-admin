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

package com.jad.common.constant;

/**
 * Redis操作使用到的常量
 *
 * @author cxxwl96
 * @since 2021/6/20 12:56
 */
public class RedisConst {

    // Redis中存放验证码的key
    public static final String SECURITY_VERIFY_KEY = "security-verify-key-";

    // Redis中存放用户认证的jwt
    public static final String SECURITY_USER_AUTHENTICATE_TOKEN_KEY_PREFIX = "security-user-authenticate-token-";

    // Redis中存放用户授权信息的key
    public static final String SECURITY_USER_GRANTED_AUTHORITY = "security-user-granted-authority";

    // Redis中存放用户
    public static final String SYSTEM_USER = "system-user";

    // Redis中存放用户菜单列表
    public static final String SYSTEM_USER_MENU_LIST = "system-user-menu-list";

    // Redis中存放用户菜单树
    public static final String SYSTEM_USER_MENU_TREE = "system-user-menu-tree";

    // Redis中存放角色
    public static final String SYSTEM_ROLE = "system-role";

    // Redis中存放超级管理员角色
    public static final String SYSTEM_ROLE_ADMINISTRATOR = "system-role-administrator";

    // Redis中存放的默认角色
    public static final String SYSTEM_ROLE_DEFAULT_ROLE = "system-role-default-role";

    // Redis中存放字典
    public static final String SYSTEM_DICT = "system-dict";

    // Redis中存放设置树
    public static final String SYSTEM_USER_SETTING_TREE = "system-user-setting-tree";
}
