package com.pragma.user_service.domain.service;

import com.pragma.user_service.domain.util.RegexPatterns;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class EmailValidationService {
    private static final Pattern PATTERN = Pattern.compile(RegexPatterns.EMAIL_CODE_PATTERN);

    public boolean isValid(String email) {
        return email != null && PATTERN.matcher(email).matches();
    }
}

