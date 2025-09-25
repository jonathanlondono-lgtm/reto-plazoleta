package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.INVALID_USER_ID;

public class InvalidUserIdException extends RuntimeException {
    public InvalidUserIdException() {
        super(INVALID_USER_ID);
    }
}

