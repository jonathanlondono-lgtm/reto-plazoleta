package com.pragma.user_service.application.ports.output;

import com.pragma.user_service.domain.model.Role;
import java.util.List;

public interface IRolePersistencePort {
    List<Role> getRolesByUserId(Long userId);
}
