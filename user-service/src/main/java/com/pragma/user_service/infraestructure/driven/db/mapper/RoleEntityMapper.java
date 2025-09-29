package com.pragma.user_service.infraestructure.driven.db.mapper;

import com.pragma.user_service.domain.model.Role;
import com.pragma.user_service.infraestructure.driven.db.entity.RoleEntity;

public class RoleEntityMapper {
    private RoleEntityMapper() {}

    public static Role toDomain(RoleEntity entity) {
        return Role.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static RoleEntity toEntity(Role role) {
        RoleEntity entity = new RoleEntity();
        entity.setId(role.getId());
        entity.setName(role.getName());
        return entity;
    }
}
