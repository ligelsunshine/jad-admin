/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * Result
 *
 * @author cxxwl96
 * @since 2021/6/18 23:41
 */
@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

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
