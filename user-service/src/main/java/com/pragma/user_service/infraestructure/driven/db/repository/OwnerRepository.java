package com.pragma.user_service.infraestructure.driven.db.repository;

import com.pragma.user_service.infraestructure.driven.db.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
    boolean existsByTaxId(String taxId);
}


