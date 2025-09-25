package com.pragma.user_service.infraestructure.driven.db.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
}
