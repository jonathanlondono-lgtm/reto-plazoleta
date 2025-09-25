package com.pragma.user_service.domain.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneValidationServiceTest {
    private final PhoneValidationService service = new PhoneValidationService();

    @Test
    void testIsValid_withValidPhoneWithPlus_returnsTrue() {
        String phone = "+573005698325";
        boolean result = service.isValid(phone);
        assertTrue(result);
    }

    @Test
    void testIsValid_withValidPhoneWithoutPlus_returnsTrue() {
        String phone = "573005698325";
        boolean result = service.isValid(phone);
        assertTrue(result);
    }

    @Test
    void testIsValid_withTooLongPhone_returnsFalse() {
        String phone = "+57300569832599";
        boolean result = service.isValid(phone);
        assertFalse(result);
    }

    @Test
    void testIsValid_withInvalidCharacters_returnsFalse() {
        String phone = "+57abc5698325";
        boolean result = service.isValid(phone);
        assertFalse(result);
    }

    @Test
    void testIsValid_withEmptyPhone_returnsFalse() {
        String phone = "";
        boolean result = service.isValid(phone);
        assertFalse(result);
    }
}

