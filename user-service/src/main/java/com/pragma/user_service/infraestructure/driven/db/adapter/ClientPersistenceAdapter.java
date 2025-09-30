package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.application.ports.output.IClientPersistencePort;
import com.pragma.user_service.domain.model.Client;
import com.pragma.user_service.infraestructure.driven.db.entity.ClientEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.mapper.ClientEntityMapper;
import com.pragma.user_service.infraestructure.driven.db.repository.ClientRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientPersistenceAdapter implements IClientPersistencePort {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    @Override
    public boolean existsByTaxId(String taxId) {
        return clientRepository.existsByTaxId(taxId);
    }

    @Override
    public Client saveClient(Client client) {
        UserEntity persistedUser = userRepository.findById(client.getUserId()).orElseThrow();
        ClientEntity entity = ClientEntityMapper.toEntity(client, persistedUser);
        ClientEntity saved = clientRepository.save(entity);
        return ClientEntityMapper.toDomain(saved);
    }


}

