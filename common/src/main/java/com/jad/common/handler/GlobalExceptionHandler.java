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

package com.jad.common.handler;

import com.jad.common.exception.BadRequestException;
import com.jad.common.exception.CaptchaException;
import com.jad.common.exception.UnauthorizedException;
import com.jad.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLSyntaxErrorException;

/**
 * 全局异常处理
 *
 * @author cxxwl96
 * @since 2021/6/18 23:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 非法数据异常
     *
     * @param e 异常
     * @return 响应结果 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            IllegalArgumentException.class, HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class,
            MethodArgumentConversionNotSupportedException.class, IllegalStateException.class, MultipartException.class
    })
    public Result<?> illegalDataHandler(Exception e) {
        return processFailed(HttpStatus.BAD_REQUEST.value(), "非法数据请求", e);
    }

    /**
     * 实体校验异常
     *
     * @param e 异常
     * @return 响应结果 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> handler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().stream().findFirst().orElse(null);
        if (objectError != null) {
            return processFailed(HttpStatus.BAD_REQUEST.value(), objectError.getDefaultMessage(), e);
        }
        return processFailed(HttpStatus.BAD_REQUEST.value(), "实体校验异常", e);
    }

    /**
     * 验证码错误
     *
     * @param e 异常
     * @return 响应结果 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CaptchaException.class)
    public Result<?> handler(CaptchaException e) {
        return processFailed(HttpStatus.BAD_REQUEST.value(), "验证码错误", e);
    }

    /**
     * 错误请求异常
     *
     * @param e 异常
     * @return 响应结果 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public Result<?> handler(BadRequestException e) {
        Result<?> result = e.getResult();
        if (e.isNeedPrintStackTrace()) {
            result = processFailed(HttpStatus.BAD_REQUEST.value(), "错误的请求", e);
        } else {
            log.error(e.getMessage());
        }
        return result != null ? result : Result.failed(e.getMessage());
    }

    /**
     * 错误请求异常
     *
     * @param e 异常
     * @return 响应结果 400
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedException.class)
    public Result<?> handler(UnauthorizedException e) {
        return processFailed(HttpStatus.UNAUTHORIZED.value(), "未认证", e);
    }

    /**
     * 权限不足
     *
     * @param e 异常
     * @return 响应结果 403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result<?> handler(AccessDeniedException e) {
        return processFailed(HttpStatus.FORBIDDEN.value(), "权限不足", e);
    }

    /**
     * 不支持的请求方式
     *
     * @param e 异常
     * @return 响应结果 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> handler(HttpRequestMethodNotSupportedException e) {
        return processFailed(HttpStatus.NOT_FOUND.value(), "不支持的请求方式", e);
    }

    /**
     * 404异常
     *
     * @param e 异常
     * @return 响应结果 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handler(NoHandlerFoundException e) {
        return processFailed(HttpStatus.NOT_FOUND.value(), "您访问的资源不存在", e);
    }

    /**
     * Redis连接失败异常
     *
     * @param e 异常
     * @return 响应结果 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RedisConnectionFailureException.class)
    public Result<?> handler(RedisConnectionFailureException e) {
        return processFailed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误：Redis连接失败异常", e);
    }

    /**
     * 数据库添加多个key重复异常
     *
     * @param e 异常
     * @return 响应结果 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result<?> handler(DuplicateKeyException e) {
        return processFailed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误：数据库添加多个key重复异常", e);
    }

    /**
     * 空指针异常
     *
     * @param e 异常
     * @return 响应结果 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NullPointerException.class)
    public Result<?> handler(NullPointerException e) {
        return processFailed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误：空指针异常", e);
    }

    /**
     * 空指针异常
     *
     * @param e 异常
     * @return 响应结果 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public Result<?> handler(SQLSyntaxErrorException e) {
        return processFailed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "数据库异常", e);
    }

    /**
     * 总异常
     *
     * @param e 异常
     * @return 响应结果 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Result<?> handler(Exception e) {
        return processFailed(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常", e);
    }

    private Result<?> processFailed(int httpStatus, String msg, Exception e) {
        log.error(msg, e);
        return Result.failed(httpStatus, msg, e.getMessage());
    }

}
