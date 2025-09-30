package com.pragma.user_service.infraestructure.driven.db.mapper;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.domain.model.Role;
import com.pragma.user_service.infraestructure.driven.db.entity.RoleEntity;

public class UserEntityMapper {
    private UserEntityMapper() {}

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .enabled(entity.getEnabled())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .birthDate(entity.getBirthDate())
                .roles(entity.getRoles() != null ? entity.getRoles().stream().map(UserEntityMapper::roleEntityToDomain).toList() : null)
                .build();
    }

    public static UserEntity toEntity(User user) {
        if (user == null) return null;
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt() != null ? user.getCreatedAt() : java.time.LocalDateTime.now())
                .updatedAt(user.getUpdatedAt())
                .birthDate(user.getBirthDate())
                .roles(user.getRoles() != null ? user.getRoles().stream().map(UserEntityMapper::roleDomainToEntity).toList() : null)
                .build();
    }

    private static Role roleEntityToDomain(RoleEntity entity) {
        if (entity == null) return null;
        return Role.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    private static RoleEntity roleDomainToEntity(Role role) {
        if (role == null) return null;
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}