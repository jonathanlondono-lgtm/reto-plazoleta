package com.pragma.user_service.infraestructure.driven.db.repository;

import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}