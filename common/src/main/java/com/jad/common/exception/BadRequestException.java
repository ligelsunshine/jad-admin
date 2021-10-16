/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.exception;

import com.jad.common.lang.Result;

import java.util.Locale;

import lombok.Getter;

/**
 * 错误请求异常
 *
 * @author cxxwl96
 * @since 2021/6/27 16:27
 */
@Getter
public class BadRequestException extends RuntimeException {
    private final Result result;

    public BadRequestException(Result result) {
        super(result.getMsg());
        this.result = result;
    }

    public BadRequestException(String msg, Object... obj) {
        super(String.format(Locale.ROOT, msg, obj));
        final String format = String.format(Locale.ROOT, msg, obj);
        this.result = Result.failed(format);
    }
}
