package com.pragma.user_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long userId;
    private String fullName;
    private LocalDate hireDate;
    private Double salary;
    private Long restaurantId;
    private String phone;
    private String taxId;
    private User user;
}
