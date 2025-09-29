package com.pragma.user_service.application.ports.output;

import com.pragma.user_service.domain.model.User;

public interface IUserPersistencePort {
    User saveUser(User user, String roleName);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User findByUsername(String username);
}
