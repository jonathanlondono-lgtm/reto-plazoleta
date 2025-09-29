package com.pragma.user_service.infraestructure.driver.rest.mapper;

import com.pragma.user_service.domain.model.Role;
import com.pragma.user_service.infraestructure.driver.rest.dto.RoleResponseDto;

public class RoleRestMapper {
    private RoleRestMapper() {}

    public static RoleResponseDto toResponseDto(Role role) {
        return new RoleResponseDto(role.getId(), role.getName());
    }
}