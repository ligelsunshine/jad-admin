/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.base.controller;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BasicErrorController
 *
 * @author cxxwl96
 * @since 2021/7/22 23:09
 */
@RestController
@RequestMapping( {"/error"})
public class JadBasicErrorController extends BasicErrorController {

    public JadBasicErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的JSON响应
     *
     * @param request 请求
     * @return 无实际返回值，因为已经抛出异常
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        String msg;
        switch (status.value()) {
            case 404:
                msg = "您访问的资源不存在";
                break;
            default:
                msg = "未知错误";
        }
        final HashMap<String, Object> body = new HashMap<>();
        body.put("code", status.value());
        body.put("msg", msg);
        body.put("data", null);
        return new ResponseEntity<>(body, status);
    }

    /**
     * 覆盖默认的HTML响应
     *
     * @param request 请求
     * @param response 响应
     * @return 无实际返回值，因为已经抛出异常
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        return super.errorHtml(request, response);
    }
}
