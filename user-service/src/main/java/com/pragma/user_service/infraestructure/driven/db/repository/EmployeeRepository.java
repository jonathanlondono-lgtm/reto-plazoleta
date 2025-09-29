package com.pragma.user_service.infraestructure.driven.db.repository;

import com.pragma.user_service.infraestructure.driven.db.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    boolean existsByTaxId(String taxId);
    Optional<EmployeeEntity> findByUserId(Long userId);
}

