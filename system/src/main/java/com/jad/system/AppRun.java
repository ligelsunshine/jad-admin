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

package com.jad.system;

import com.jad.security.annotations.PermitAuthScan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * AppRun
 *
 * @author cxxwl96
 * @since 2021/6/17 23:39
 */
// DataSourceAutoConfiguration：去掉springboot 默认的数据源配置
@PermitAuthScan( {"com.jad.**"})
@ComponentScan( {"com.jad.**"})
@MapperScan( {"com.jad.**.mapper"})
@SpringBootApplication(scanBasePackages = "com.jad", exclude = {DataSourceAutoConfiguration.class})
public class AppRun {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppRun.class);
        app.setBannerMode(Banner.Mode.LOG); // Print the banner to the log file.
        app.run(args);
    }
}
