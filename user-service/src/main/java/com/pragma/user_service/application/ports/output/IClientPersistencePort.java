package com.pragma.user_service.application.ports.output;

import com.pragma.user_service.domain.model.Client;

public interface IClientPersistencePort {
    Client saveClient(Client client);
    boolean existsByTaxId(String taxId);
}

