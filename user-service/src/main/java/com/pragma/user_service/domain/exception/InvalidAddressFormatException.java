package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.INVALID_ADDRESS_FORMAT;

public class InvalidAddressFormatException extends RuntimeException {
    public InvalidAddressFormatException() {
        super(INVALID_ADDRESS_FORMAT);
    }
}

