/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 *
 * @author cxxwl96
 * @since 2021/6/20 20:56
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
