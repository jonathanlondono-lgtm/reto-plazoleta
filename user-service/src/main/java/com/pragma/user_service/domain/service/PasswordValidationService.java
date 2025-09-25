package com.pragma.user_service.domain.service;

import com.pragma.user_service.domain.util.RegexPatterns;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class PasswordValidationService {
    private static final Pattern PATTERN = Pattern.compile(RegexPatterns.PASSWORD_CODE_PATTERN);
    public boolean isValid(String password) {
        return password != null && PATTERN.matcher(password).matches();
    }
}

