package com.pragma.user_service.global;

import com.pragma.user_service.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
        UserNotFoundException.class,
        UsernameAlreadyExistsException.class,
        UserIsNotAdultException.class,
        RoleNotFoundException.class,
        PasswordTooShortException.class,
        PasswordsDoNotMatchException.class,
        InvalidUserIdException.class,
        InvalidPhoneFormatException.class,
        InvalidFullNameFormatException.class,
        InvalidEmailFormatException.class,
        InvalidAddressFormatException.class,
        EmailAlreadyExistsException.class,
        ClientNotAuthorizedException.class
    })
    @ResponseBody
    public ResponseEntity<Object> handleCustomExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getClass().getSimpleName());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", "InternalServerError");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

