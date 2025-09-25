package com.pragma.user_service.infraestructure.driven.db.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "owners")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerEntity {

    @Id
    private Long userId; // ⚠️ si usas @MapsId no necesitas este campo duplicado

    @OneToOne
    @MapsId // usa el mismo valor que el id de User
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "tax_id", length = 50, unique = true)
    private String taxId;

    @Column(name = "contact_info", length = 255)
    private String contactInfo;
}