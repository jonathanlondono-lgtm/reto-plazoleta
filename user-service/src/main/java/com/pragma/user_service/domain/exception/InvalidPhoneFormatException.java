package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.INVALID_PHONE_FORMAT;

public class InvalidPhoneFormatException extends RuntimeException {
    public InvalidPhoneFormatException() {
        super(INVALID_PHONE_FORMAT);
    }
}
