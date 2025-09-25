package com.pragma.user_service.infraestructure.driven.db.mapper;

import com.pragma.user_service.domain.model.Owner;
import com.pragma.user_service.infraestructure.driven.db.entity.OwnerEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;

public class OwnerEntityMapper {
    public static Owner toDomain(OwnerEntity entity) {
        if (entity == null) return null;
        return Owner.builder()
                .userId(entity.getUserId())
                .fullName(entity.getFullName())
                .taxId(entity.getTaxId())
                .contactInfo(entity.getContactInfo())
                .user(com.pragma.user_service.infraestructure.driven.db.mapper.UserEntityMapper.toDomain(entity.getUser()))
                .build();
    }

    public static OwnerEntity toEntity(Owner owner, UserEntity persistedUser) {
        return OwnerEntity.builder()
                .user(persistedUser) // importante: asigna el User gestionado
                .fullName(owner.getFullName())
                .taxId(owner.getTaxId())
                .contactInfo(owner.getContactInfo())
                .build();
    }
}
