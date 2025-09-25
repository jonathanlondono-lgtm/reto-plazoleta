package com.pragma.user_service.domain.exception;

import static com.pragma.user_service.domain.util.ExceptionMessages.USER_IS_NOT_ADULT;

public class UserIsNotAdultException extends RuntimeException {
    public UserIsNotAdultException() {
        super(USER_IS_NOT_ADULT);
    }
}

