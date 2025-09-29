package com.pragma.user_service.infraestructure.driven.db.mapper;

import com.pragma.user_service.domain.model.Employee;
import com.pragma.user_service.infraestructure.driven.db.entity.EmployeeEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import java.math.BigDecimal;

public class EmployeeEntityMapper {
    private EmployeeEntityMapper() {}

    public static EmployeeEntity toEntity(Employee employee, UserEntity persistedUser) {
        return EmployeeEntity.builder()
                .user(persistedUser) // igual que Owner: asigna el User gestionado
                .fullName(employee.getFullName())
                .hireDate(employee.getHireDate())
                .salary(employee.getSalary() != null ? BigDecimal.valueOf(employee.getSalary()) : null)
                .restaurantId(employee.getRestaurantId())
                .phone(employee.getPhone())
                .taxId(employee.getTaxId())
                .build();
    }

    public static Employee toDomain(EmployeeEntity entity) {
        if (entity == null) return null;
        return Employee.builder()
                .userId(entity.getUserId())
                .fullName(entity.getFullName())
                .hireDate(entity.getHireDate())
                .salary(entity.getSalary() != null ? entity.getSalary().doubleValue() : null)
                .restaurantId(entity.getRestaurantId())
                .phone(entity.getPhone())
                .taxId(entity.getTaxId())
                .user(entity.getUser() != null ? com.pragma.user_service.infraestructure.driven.db.mapper.UserEntityMapper.toDomain(entity.getUser()) : null)
                .build();
    }
}
