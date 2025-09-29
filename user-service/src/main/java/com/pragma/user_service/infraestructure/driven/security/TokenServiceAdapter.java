package com.pragma.user_service.infraestructure.driven.security;

import com.pragma.user_service.application.ports.output.ITokenServicePort;
import com.pragma.user_service.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenServiceAdapter implements ITokenServicePort {
    private final JwtService jwtService;

    @Override
    public String generateToken(User user) {
        return jwtService.generateToken(user);
    }
}

