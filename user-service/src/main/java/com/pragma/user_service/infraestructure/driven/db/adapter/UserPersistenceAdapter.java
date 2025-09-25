package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.application.ports.output.IUserPersistencePort;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.infraestructure.driven.db.entity.RoleEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.mapper.UserEntityMapper;
import com.pragma.user_service.infraestructure.driven.db.repository.RoleRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements IUserPersistencePort {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        System.out.println("Buscando rol: 'Propietario'");
        RoleEntity ownerRole = roleRepository.findByNameIgnoreCase("Propietario")
            .orElseThrow(() -> new RuntimeException("Role Propietario not found"));
        System.out.println("Rol encontrado: " + ownerRole);
        UserEntity entity = UserEntityMapper.toEntity(user);
        entity.setRoles(Collections.singletonList(ownerRole));
        UserEntity saved = userRepository.save(entity);
        return UserEntityMapper.toDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
