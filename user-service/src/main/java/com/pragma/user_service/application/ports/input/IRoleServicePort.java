package com.pragma.user_service.application.ports.input;

import com.pragma.user_service.domain.model.Role;
import java.util.List;

public interface IRoleServicePort {
    List<Role> getRolesByUserId(Long userId);
}
