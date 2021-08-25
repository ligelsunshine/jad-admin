/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置类
 *
 * @author cxxwl96
 * @since 2021/6/17 23:39
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.jad.*"})
public class SwaggerConfig {

    @Value("${jad.swagger.enable}")
    private Boolean enable;

    @Value("${jad.swagger.path-mapping}")
    private String pathMapping;

    @Value("${jad.swagger.api-info.title}")
    private String title;

    @Value("${jad.swagger.api-info.description}")
    private String description;

    @Value("${jad.swagger.api-info.version}")
    private String version;

    @Value("${jad.swagger.api-info.contact.name}")
    private String name;

    @Value("${jad.swagger.api-info.contact.url}")
    private String url;

    @Value("${jad.swagger.api-info.contact.email}")
    private String email;

    @Value("${jad.swagger.api-info.license}")
    private String license;

    @Value("${jad.swagger.api-info.license-url}")
    private String licenseUrl;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).enable(enable)
            .pathMapping("/")
            .apiInfo(apiInfo())
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts())
            .select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 配置Swagger信息apiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
            .description(description)
            .version(version)
            .contact(new Contact(name, url, email))
            .license(license)
            .licenseUrl(licenseUrl)
            .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex("^(?!auth).*$"))
            .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
