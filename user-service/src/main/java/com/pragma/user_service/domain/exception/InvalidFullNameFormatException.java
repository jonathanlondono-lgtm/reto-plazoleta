package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.INVALID_FULLNAME_FORMAT;

public class InvalidFullNameFormatException extends RuntimeException {
    public InvalidFullNameFormatException() {
        super(INVALID_FULLNAME_FORMAT);
    }
}

