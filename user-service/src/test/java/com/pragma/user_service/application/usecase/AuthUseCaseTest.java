package com.pragma.user_service.application.usecase;

import com.pragma.user_service.application.ports.output.IUserAuthPersistencePort;
import com.pragma.user_service.application.ports.output.ITokenServicePort;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.model.Role;
import com.pragma.user_service.domain.exception.UserNotFoundException;
import com.pragma.user_service.domain.exception.InvalidCredentialsException;
import com.pragma.user_service.domain.exception.ClientNotAuthorizedException;
import com.pragma.user_service.domain.exception.RoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthUseCaseTest {
    @Mock
    private IUserAuthPersistencePort userAuthPersistencePort;
    @Mock
    private ITokenServicePort tokenServicePort;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthUseCase authUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        // Arrange
        String username = "user1";
        String password = "pass123";
        User user = new User();
        user.setUsername(username);
        user.setPassword("hashedpass");
        user.setEnabled(true);
        user.setRoles(List.of(new Role(1L, "PROPIETARIO")));
        when(userAuthPersistencePort.findByUsername(username)).thenReturn(user);
        when(passwordEncoder.matches(password, "hashedpass")).thenReturn(true);
        when(tokenServicePort.generateToken(user)).thenReturn("token123");
        // Act
        String token = authUseCase.login(username, password);
        // Assert
        assertEquals("token123", token);
    }

    @Test
    void testLogin_UserNotFound() {
        // Arrange
        when(userAuthPersistencePort.findByUsername("user1")).thenReturn(null);
        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> authUseCase.login("user1", "pass123"));
    }

    @Test
    void testLogin_UserDisabled() {
        // Arrange
        User user = new User();
        user.setUsername("user1");
        user.setPassword("hashedpass");
        user.setEnabled(false);
        when(userAuthPersistencePort.findByUsername("user1")).thenReturn(user);
        // Act & Assert
        assertThrows(ClientNotAuthorizedException.class, () -> authUseCase.login("user1", "pass123"));
    }

    @Test
    void testLogin_InvalidPassword() {
        // Arrange
        User user = new User();
        user.setUsername("user1");
        user.setPassword("hashedpass");
        user.setEnabled(true);
        user.setRoles(List.of(new Role(1L, "PROPIETARIO")));
        when(userAuthPersistencePort.findByUsername("user1")).thenReturn(user);
        when(passwordEncoder.matches("pass123", "hashedpass")).thenReturn(false);
        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> authUseCase.login("user1", "pass123"));
    }

    @Test
    void testLogin_NoRoles() {
        // Arrange
        User user = new User();
        user.setUsername("user1");
        user.setPassword("hashedpass");
        user.setEnabled(true);
        user.setRoles(Collections.emptyList());
        when(userAuthPersistencePort.findByUsername("user1")).thenReturn(user);
        when(passwordEncoder.matches("pass123", "hashedpass")).thenReturn(true);
        // Act & Assert
        assertThrows(RoleNotFoundException.class, () -> authUseCase.login("user1", "pass123"));
    }
}

