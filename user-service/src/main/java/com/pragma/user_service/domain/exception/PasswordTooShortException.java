package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.PASSWORD_TOO_SHORT;

public class PasswordTooShortException extends RuntimeException {
    public PasswordTooShortException() {
        super(PASSWORD_TOO_SHORT);
    }
}

