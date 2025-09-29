package com.pragma.user_service.application.ports.output;

import com.pragma.user_service.domain.model.User;

public interface IUserAuthPersistencePort {
    User findByUsername(String username);
}

