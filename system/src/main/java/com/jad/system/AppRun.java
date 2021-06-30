/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * AppRun
 *
 * @author cxxwl96
 * @since 2021/6/17 23:39
 */
@SpringBootApplication(scanBasePackages = "com.jad")
@MapperScan({"com.jad.**.mapper"})
@ComponentScan({"com.jad.**"})
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }
}
