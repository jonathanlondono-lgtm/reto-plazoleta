package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.application.ports.output.IRolePersistencePort;
import com.pragma.user_service.domain.model.Role;
import com.pragma.user_service.infraestructure.driven.db.mapper.RoleEntityMapper;
import com.pragma.user_service.infraestructure.driven.db.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements IRolePersistencePort {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleRepository.findRolesByUserId(userId)
                .stream()
                .map(RoleEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
