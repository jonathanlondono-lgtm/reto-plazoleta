package com.pragma.user_service.domain.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AgeValidationServiceTest {
    private final AgeValidationService service = new AgeValidationService();

    @Test
    void testIsAdult_withAdultBirthDate_returnsTrue() {
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        boolean result = service.isAdult(birthDate);
        assertTrue(result);
    }

    @Test
    void testIsAdult_withMinorBirthDate_returnsFalse() {
        LocalDate birthDate = LocalDate.now().minusYears(17);
        boolean result = service.isAdult(birthDate);
        assertFalse(result);
    }

    @Test
    void testIsAdult_withNullBirthDate_returnsFalse() {
        LocalDate birthDate = null;
        boolean result = service.isAdult(birthDate);
        assertFalse(result);
    }
}

