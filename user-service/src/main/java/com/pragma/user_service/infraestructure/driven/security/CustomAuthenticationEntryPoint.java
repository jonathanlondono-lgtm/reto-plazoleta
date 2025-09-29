package com.pragma.user_service.infraestructure.driven.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.user_service.domain.util.ExceptionMessages;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Unauthorized");
        body.put("message", ExceptionMessages.INVALID_CREDENTIALS);
        body.put("timestamp", java.time.LocalDateTime.now().toString());
        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}

