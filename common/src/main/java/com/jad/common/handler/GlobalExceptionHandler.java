/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.handler;

import com.jad.common.exception.BadRequestException;
import com.jad.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

/**
 * 全局异常处理
 *
 * @author cxxwl96
 * @since 2021/6/18 23:56
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 非法数据异常
     *
     * @param e 异常
     * @return 响应结果 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) {
        log.error("非法数据异常：----------{}", e.getMessage());
        return Result.failed(e.getMessage());
    }

    /**
     * 实体校验异常
     *
     * @param e 异常
     * @return 响应结果 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常：----------{}", e.getMessage());
        ObjectError objectError = e.getBindingResult().getAllErrors().stream().findFirst().orElse(null);
        if (objectError != null) {
            return Result.failed(objectError.getDefaultMessage());
        }
        return Result.failed(e.getMessage());
    }

    /**
     * 权限不足
     *
     * @param e 异常
     * @return 响应结果 403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result handler(AccessDeniedException e) {
        log.error("权限不足：----------{}", e.getMessage());
        return Result.failed(HttpStatus.FORBIDDEN.value(), e.getMessage(), null);
    }

    /**
     * 404异常
     *
     * @param e 异常
     * @return 响应结果 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handler(NoHandlerFoundException e) {
        log.error("404异常：----------{}", e.getMessage());
        return Result.failed(HttpStatus.NOT_FOUND.value(), "您访问的资源不存在", null);
    }

    /**
     * 错误请求异常
     *
     * @param e 异常
     * @return 响应结果
     */
    @ExceptionHandler(value = BadRequestException.class)
    public Result handler(HttpServletResponse response, BadRequestException e) {
        log.error("错误请求异常：----------{}", e.getResult().getMsg());
        e.printStackTrace();
        return Result.failed(e.getResult().getCode(), e.getResult().getMsg(), null);
    }

    /**
     * Redis连接失败异常
     *
     * @param e 异常
     * @return 响应结果 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RedisConnectionFailureException.class)
    public Result handler(RedisConnectionFailureException e) {
        log.error("Redis连接失败异常：----------{}", e.getMessage());
        e.printStackTrace();
        return Result.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误", null);
    }

    /**
     * 空指针异常
     *
     * @param e 异常
     * @return 响应结果 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NullPointerException.class)
    public Result handler(NullPointerException e) {
        log.error("空指针异常：----------{}", e.getMessage());
        e.printStackTrace();
        return Result.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误", null);
    }

    /**
     * 系统异常
     *
     * @param e 异常
     * @return 响应结果 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Result handler(Exception e) {
        log.error("系统异常：----------{}", e.getMessage());
        e.printStackTrace();
        return Result.failed(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
    }
}
