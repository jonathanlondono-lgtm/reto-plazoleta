package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.application.ports.output.IUserAuthPersistencePort;
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
public class UserPersistenceAdapter implements IUserPersistencePort, IUserAuthPersistencePort {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user, String roleName) {
        RoleEntity role = roleRepository.findByNameIgnoreCase(roleName)
            .orElseThrow(() -> new RuntimeException("Role " + roleName + " not found"));
        UserEntity entity = UserEntityMapper.toEntity(user);
        entity.setRoles(Collections.singletonList(role));
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

    @Override
    public User findByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username).orElse(null);
        return entity != null ? UserEntityMapper.toDomain(entity) : null;
    }
}
