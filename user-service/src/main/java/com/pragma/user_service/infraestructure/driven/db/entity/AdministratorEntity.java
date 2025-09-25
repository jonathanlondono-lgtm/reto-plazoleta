package com.pragma.user_service.infraestructure.driven.db.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administrators")
@Data
@Builder
@AllArgsConstructor
public class AdministratorEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;
}

