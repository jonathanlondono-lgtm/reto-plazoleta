package com.pragma.user_service.infraestructure.driven.db.mapper;

import com.pragma.user_service.domain.model.Client;
import com.pragma.user_service.infraestructure.driven.db.entity.ClientEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;

public class ClientEntityMapper {
    private ClientEntityMapper() {}

    public static ClientEntity toEntity(Client client, UserEntity persistedUser) {
        return ClientEntity.builder()
                .user(persistedUser)
                .fullName(client.getFullName())
                .phone(client.getPhone())
                .address(client.getAddress())
                .loyaltyPoints(client.getLoyaltyPoints() != null ? client.getLoyaltyPoints() : 0)
                .paymentInfo(client.getPaymentInfo() != null ? client.getPaymentInfo() : "cash")
                .taxId(client.getTaxId())
                .build();
    }

    public static Client toDomain(ClientEntity entity) {
        if (entity == null) return null;
        return Client.builder()
                .userId(entity.getUserId())
                .fullName(entity.getFullName())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .loyaltyPoints(entity.getLoyaltyPoints())
                .paymentInfo(entity.getPaymentInfo())
                .taxId(entity.getTaxId())
                .user(entity.getUser() != null ? UserEntityMapper.toDomain(entity.getUser()) : null)
                .build();
    }
}
