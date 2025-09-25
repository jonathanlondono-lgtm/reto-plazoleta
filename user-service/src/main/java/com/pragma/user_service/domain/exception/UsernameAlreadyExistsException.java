package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.USERNAME_ALREADY_EXISTS;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {
        super(USERNAME_ALREADY_EXISTS);
    }
}

