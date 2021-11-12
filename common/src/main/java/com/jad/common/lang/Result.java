/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.lang;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 响应结果
 *
 * @author cxxwl96
 * @since 2021/6/18 23:41
 */
@Data
public class Result implements Serializable {
    public static final int CODE_SUCCESS = 200;

    public static final int CODE_FAILED = 400;

    @ApiModelProperty(value = "响应码")
    private int code;

    @ApiModelProperty(value = "响应消息")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private Object data;

    public static Result formatFailed(String format, Object... args) {
        return Result.failed(String.format(format, args));
    }

    public static Result formatSuccess(String format, Object... args) {
        return Result.success(String.format(format, args));
    }

    public static Result success() {
        return success(200, "操作成功", null);
    }

    public static Result success(String msg) {
        return success(200, msg, null);
    }

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(String msg, Object data) {
        return success(200, msg, data);
    }

    public static Result success(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result failed() {
        return failed(400, "操作失败", null);
    }

    public static Result failed(String msg) {
        return failed(400, msg, null);
    }

    public static Result failed(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
