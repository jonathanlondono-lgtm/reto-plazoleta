package com.pragma.user_service.infraestructure.driver.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "User Service API",
        version = "1.0",
        description = "API para gestión de usuarios y propietarios"
    )
)
@Configuration
public class OpenApiConfig {
}

