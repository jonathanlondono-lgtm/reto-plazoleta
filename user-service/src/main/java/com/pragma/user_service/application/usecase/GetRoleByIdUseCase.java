package com.pragma.user_service.application.usecase;

import com.pragma.user_service.application.ports.input.IRoleServicePort;
import com.pragma.user_service.application.ports.output.IRolePersistencePort;
import com.pragma.user_service.domain.model.Role;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class GetRoleByIdUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public GetRoleByIdUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return rolePersistencePort.getRolesByUserId(userId);
    }
}
