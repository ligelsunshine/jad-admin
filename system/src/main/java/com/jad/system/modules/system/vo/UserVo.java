/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.system.modules.system.vo;

import com.jad.common.entity.Role;
import com.jad.common.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户信息
 *
 * @author cxxwl96
 * @since 2021/6/29 00:58
 */
@Data
@Accessors(chain = true)
public class UserVo {

    private User user;

    private List<Role> roles;
}
