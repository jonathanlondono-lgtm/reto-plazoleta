package com.pragma.user_service.infraestructure.driven.security;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.model.Role;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;

import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    private static final long EXPIRATION_TIME = 86400000; // 1 día en ms

    public String generateToken(User user) {
        javax.crypto.SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }
}
