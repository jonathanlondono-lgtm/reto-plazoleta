package com.pragma.user_service.domain.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidationServiceTest {
    private final EmailValidationService service = new EmailValidationService();

    @Test
    void testIsValid_withValidEmail_returnsTrue() {
        String email = "test@example.com";
        boolean result = service.isValid(email);
        assertTrue(result);
    }

    @Test
    void testIsValid_withInvalidEmail_returnsFalse() {
        String email = "invalid-email";
        boolean result = service.isValid(email);
        assertFalse(result);
    }

    @Test
    void testIsValid_withEmptyEmail_returnsFalse() {
        String email = "";
        boolean result = service.isValid(email);
        assertFalse(result);
    }
}

