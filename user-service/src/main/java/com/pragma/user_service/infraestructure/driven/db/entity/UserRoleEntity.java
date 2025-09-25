package com.pragma.user_service.infraestructure.driven.db.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity {
    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}

