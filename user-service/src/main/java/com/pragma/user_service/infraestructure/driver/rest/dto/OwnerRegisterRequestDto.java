package com.pragma.user_service.infraestructure.driver.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "DTO para registrar un propietario")
public class OwnerRegisterRequestDto {
    @Schema(description = "Correo electrónico del propietario", example = "owner@example.com")
    private String email;
    @Schema(description = "Nombre de usuario", example = "owner1")
    private String username;
    @Schema(description = "Contraseña del propietario", example = "Password123")
    private String password;
    @Schema(description = "Nombre completo del propietario", example = "Juan Pérez")
    private String fullName;
    @Schema(description = "Identificación tributaria", example = "123456789")
    private String taxId;
    @Schema(description = "Información de contacto", example = "+57 3001234567")
    private String contactInfo;
    @Schema(description = "Fecha de nacimiento", example = "1990-05-15")
    private LocalDate birthDate;
}
