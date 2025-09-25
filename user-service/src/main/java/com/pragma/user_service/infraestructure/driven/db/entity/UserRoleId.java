package com.pragma.user_service.infraestructure.driven.db.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class UserRoleId implements java.io.Serializable {
    private Long userId;
    private Long roleId;
}
