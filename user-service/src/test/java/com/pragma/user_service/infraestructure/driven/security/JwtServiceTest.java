package com.pragma.user_service.infraestructure.driven.security;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {
    private final JwtService jwtService = new JwtService();
    private static final String SECRET = "LONque1234567890LONque1234567890abcd";

    @Test
    void testGenerateToken_WithRoles() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setRoles(List.of(new Role(1L, "PROPIETARIO"), new Role(2L, "ADMIN")));
        // Act
        String token = jwtService.generateToken(user);
        // Assert
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        assertEquals("testuser", claims.getSubject());
        assertEquals("PROPIETARIO,ADMIN", claims.get("authorities"));
    }

    @Test
    void testGenerateToken_WithoutRoles() {
        // Arrange
        User user = new User();
        user.setUsername("norolesuser");
        user.setRoles(List.of());
        // Act
        String token = jwtService.generateToken(user);
        // Assert
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        assertEquals("norolesuser", claims.getSubject());
        assertEquals("", claims.get("authorities"));
    }
}

