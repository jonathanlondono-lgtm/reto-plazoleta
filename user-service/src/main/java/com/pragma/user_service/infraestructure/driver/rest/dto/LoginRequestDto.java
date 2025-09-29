package com.pragma.user_service.infraestructure.driver.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequestDto {
    @Schema(description = "Nombre de usuario para autenticación", example = "usuario123")
    private String username;
    @Schema(description = "Contraseña del usuario", example = "Password123@")
    private String password;
}
