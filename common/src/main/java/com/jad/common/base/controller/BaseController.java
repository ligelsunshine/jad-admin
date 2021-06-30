/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.base.controller;

import com.jad.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseController
 *
 * @author cxxwl96
 * @since 2021/6/18 22:06
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected RedisUtil redisUtil;


}
