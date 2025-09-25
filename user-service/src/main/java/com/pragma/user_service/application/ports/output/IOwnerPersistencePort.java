package com.pragma.user_service.application.ports.output;

import com.pragma.user_service.domain.model.Owner;

public interface IOwnerPersistencePort {
    Owner saveOwner(Owner owner);
    boolean existsByTaxId(String taxId);
}

