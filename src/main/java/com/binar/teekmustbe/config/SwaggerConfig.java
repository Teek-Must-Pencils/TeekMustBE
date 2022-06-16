package com.binar.teekmustbe.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI custom(@Value("Teek Must Pencils Market Place") String appDescription, @Value("v1.0.0") String appversion) {
        return new OpenAPI().info(new Info()
                .title("Documentation for Market Place API's")
                .version(appversion)
                .description(appDescription)
                .termsOfService("https://swagger.io/terms")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://springdocs.org")));
    }
}
