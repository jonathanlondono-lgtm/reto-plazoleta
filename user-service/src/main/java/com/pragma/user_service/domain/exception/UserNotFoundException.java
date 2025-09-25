package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.USER_NOT_FOUND;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {super(USER_NOT_FOUND);
    }
}

