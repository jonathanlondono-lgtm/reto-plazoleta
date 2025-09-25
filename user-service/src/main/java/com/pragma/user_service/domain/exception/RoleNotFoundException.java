package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.ROLE_NOT_FOUND;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super(ROLE_NOT_FOUND);
    }
}

