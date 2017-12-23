package com.gilab.wjj.admin.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerBootConfig
 * Author: yuankui
 * <p>
 * Change log:
 */
@EnableSwagger2
@Configuration
@ComponentScan(basePackages = {"com.gilab.wjj.admin.rest"})
@Profile("dev")
public class SwaggerBootConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public Docket swaggerPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gilab.wjj.admin.rest"))
                .paths(PathSelectors.regex("/api/admin/v1/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Finance Admin RESTful API Document")
                .description("REST API powered by Swagger2")
                .version("1.0.0")
                .build();
    }
}
