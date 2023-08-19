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

package com.jad.generator.config;

import com.jad.common.utils.NamingUtil;

import lombok.Getter;

/**
 * 代码生成配置类
 *
 * @author cxxwl96
 * @since 2021/9/6 22:31
 */

public class PathConfig {
    private final static String PKG_ENTITY = "entity";

    private final static String PKG_ENUM = "enums";

    private final static String PKG_MAPPER = "mapper";

    private final static String PKG_MAPPER_XML = "xml";

    private final static String PKG_SERVICE = "service";

    private final static String PKG_SERVICE_IMPL = "impl";

    private final static String PKG_CONTROLLER = "controller";

    @Getter
    private final String basePackage;

    @Getter
    private final String rootPath;

    public PathConfig(String module) {
        String pkg = NamingUtil.toLowerCase(module);
        this.basePackage = concat(".", "com.jad", pkg);
        this.rootPath = concat("/", System.getProperty("user.dir"), module, "src/main/java", "com/jad", pkg);
    }

    public String getEntityPackage() {
        return concat(".", basePackage, PKG_ENTITY);
    }

    public String getEnumPackage() {
        return concat(".", basePackage, PKG_ENUM);
    }

    public String getMapperPackage() {
        return concat(".", basePackage, PKG_MAPPER);
    }

    public String getMapperXmlPackage() {
        return concat(".", basePackage, PKG_MAPPER, PKG_MAPPER_XML);
    }

    public String getServicePackage() {
        return concat(".", basePackage, PKG_SERVICE);
    }

    public String getServiceImplPackage() {
        return concat(".", basePackage, PKG_SERVICE, PKG_SERVICE_IMPL);
    }

    public String getControllerPackage() {
        return concat(".", basePackage, PKG_CONTROLLER);
    }

    public String getEntityPath() {
        return concat("/", rootPath, PKG_ENTITY);
    }

    public String getEnumPath() {
        return concat("/", rootPath, PKG_ENUM);
    }

    public String getMapperPath() {
        return concat("/", rootPath, PKG_MAPPER);
    }

    public String getMapperXmlPath() {
        return concat("/", rootPath, PKG_MAPPER, PKG_MAPPER_XML);
    }

    public String getServicePath() {
        return concat("/", rootPath, PKG_SERVICE);
    }

    public String getServiceImplPath() {
        return concat("/", rootPath, PKG_SERVICE, PKG_SERVICE_IMPL);
    }

    public String getControllerPath() {
        return concat("/", rootPath, PKG_CONTROLLER);
    }

    private String concat(String delimiter, String... str) {
        return String.join(delimiter, str);
    }
}

