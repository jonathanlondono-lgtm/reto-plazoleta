package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.INVALID_CREDENTIALS;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() { super(INVALID_CREDENTIALS); }
}

