package com.pragma.user_service.infraestructure.driver.rest.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegisterEmployeeRequestDto {
    private String email;
    private String username;
    private String password;
    private String fullName;
    private LocalDate hireDate;
    private Double salary;
    private Long restaurantId;
    private String phone;
    private String taxId;
    private LocalDate birthDate;
}