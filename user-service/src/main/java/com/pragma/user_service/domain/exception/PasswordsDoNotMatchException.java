package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.PASSWORDS_DO_NOT_MATCH;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException() {
        super(PASSWORDS_DO_NOT_MATCH);
    }
}

