package com.tenpo.calculator.calculator.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tenpo.calculator"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Calculator API service")
                .description("Calculator API service for Tenpo challenge")
                .termsOfServiceUrl("https://giss-ignacio.github.io")
                .contact(new Contact("Ignacio Giss", "https://giss-ignacio.github.io", "giss.ignacio@gmail.com" )).license("GNU General Public License v3.0")
                .licenseUrl("giss.ignacio@gmail.com").version("1.0").build();
    }
}
