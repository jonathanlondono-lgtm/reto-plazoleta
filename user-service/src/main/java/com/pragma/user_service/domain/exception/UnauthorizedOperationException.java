package com.pragma.user_service.domain.exception;

import com.pragma.user_service.domain.util.ExceptionMessages;

public class UnauthorizedOperationException extends RuntimeException {
    public UnauthorizedOperationException() {
        super(ExceptionMessages.UNAUTHORIZED_OPERATION);
    }
    public UnauthorizedOperationException(String message) {
        super(message);
    }

}
