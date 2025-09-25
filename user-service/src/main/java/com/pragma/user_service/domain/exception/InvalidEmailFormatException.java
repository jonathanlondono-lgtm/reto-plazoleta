package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.INVALID_EMAIL_FORMAT;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException() {
        super(INVALID_EMAIL_FORMAT);
    }
}

