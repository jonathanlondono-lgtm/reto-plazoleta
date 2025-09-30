package com.pragma.user_service.infraestructure.driven.db.repository;

import com.pragma.user_service.infraestructure.driven.db.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    boolean existsByTaxId(String taxId);
}

