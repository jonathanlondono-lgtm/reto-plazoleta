package com.pragma.user_service.domain.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AgeValidationService {
    public boolean isAdult(LocalDate birthDate) {
        if (birthDate == null) return false;
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }
}

