/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.system.modules.system.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "首页")
@RestController
public class HomeController {

    @GetMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String get() {
        return "<h2 style=\"text-align: center\">JAD启动成功</h2><p style=\"text-align: center\"><a href=\"/swagger-ui.html\">Swagger</a></p>";
    }
}
