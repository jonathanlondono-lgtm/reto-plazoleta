package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.application.ports.output.IOwnerPersistencePort;
import com.pragma.user_service.domain.model.Owner;
import com.pragma.user_service.infraestructure.driven.db.entity.OwnerEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.mapper.OwnerEntityMapper;
import com.pragma.user_service.infraestructure.driven.db.repository.OwnerRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class OwnerPersistenceAdapter implements IOwnerPersistencePort {
    private static final Logger log = LoggerFactory.getLogger(OwnerPersistenceAdapter.class);
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    @Override
    public Owner saveOwner(Owner owner) {
        log.info("Intentando guardar Owner: {}", owner);
        UserEntity persistedUser = userRepository.findById(owner.getUserId()).orElseThrow();
        log.info("UserEntity gestionado por JPA: {}", persistedUser);
        OwnerEntity entity = OwnerEntityMapper.toEntity(owner, persistedUser);
        log.info("OwnerEntity a persistir: {}", entity);
        OwnerEntity saved = ownerRepository.save(entity);
        log.info("OwnerEntity guardado: {}", saved);
        return OwnerEntityMapper.toDomain(saved);
    }

    @Override
    public boolean existsByTaxId(String taxId) {
        return ownerRepository.existsByTaxId(taxId);
    }
}
