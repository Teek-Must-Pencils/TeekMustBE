package com.binar.teekmustbe;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "Authorization", scheme = "basic", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class TeekMustBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeekMustBeApplication.class, args);
    }

}
