package com.pragma.user_service.domain.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationServiceTest {
    private final PasswordValidationService service = new PasswordValidationService();

    @Test
    void testIsValid_withValidPassword_returnsTrue() {
        String password = "Abcdef1@";
        boolean result = service.isValid(password);
        assertTrue(result);
    }

    @Test
    void testIsValid_withoutUppercase_returnsFalse() {
        String password = "abcdef1@";
        boolean result = service.isValid(password);
        assertFalse(result);
    }

    @Test
    void testIsValid_withoutLowercase_returnsFalse() {
        String password = "ABCDEF1@";
        boolean result = service.isValid(password);
        assertFalse(result);
    }

    @Test
    void testIsValid_withoutDigit_returnsFalse() {
        String password = "Abcdefg@";
        boolean result = service.isValid(password);
        assertFalse(result);
    }

    @Test
    void testIsValid_withoutSpecialChar_returnsFalse() {
        String password = "Abcdef12";
        boolean result = service.isValid(password);
        assertFalse(result);
    }

    @Test
    void testIsValid_tooShort_returnsFalse() {
        String password = "Ab1@";
        boolean result = service.isValid(password);
        assertFalse(result);
    }
}

