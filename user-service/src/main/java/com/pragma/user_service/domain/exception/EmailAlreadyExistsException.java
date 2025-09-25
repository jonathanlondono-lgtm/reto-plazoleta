package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.EMAIL_ALREADY_EXISTS;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {super(EMAIL_ALREADY_EXISTS);
    }
}
