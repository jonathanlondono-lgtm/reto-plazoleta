package com.pragma.user_service.infraestructure.driver.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRegisterRequestDto {
    private String email;
    private String username;
    private String password;
    private String fullName;
    private LocalDate birthDate;
    private String phone;
    private String taxId;

}
