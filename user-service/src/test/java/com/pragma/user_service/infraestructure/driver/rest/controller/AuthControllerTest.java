package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.ports.input.IAuthServicePort;
import com.pragma.user_service.domain.exception.InvalidCredentialsException;
import com.pragma.user_service.global.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static com.pragma.user_service.domain.util.ExceptionMessages.INVALID_CREDENTIALS;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {
    @Mock
    private IAuthServicePort authServicePort;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testLogin_Success() throws Exception {
        // Arrange
        String username = "user1";
        String password = "pass123";
        String token = "token123";
        when(authServicePort.login(username, password)).thenReturn(token);
        String requestJson = "{\"username\":\"user1\",\"password\":\"pass123\"}";
        // Act & Assert
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token));
    }

    @Test
    void testLogin_InvalidCredentials() throws Exception {
        // Arrange
        String username = "user1";
        String password = "wrongpass";
        when(authServicePort.login(username, password)).thenThrow(new InvalidCredentialsException());
        String requestJson = "{\"username\":\"user1\",\"password\":\"wrongpass\"}";
        // Act & Assert
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("InvalidCredentialsException"))
                .andExpect(jsonPath("$.message").value(INVALID_CREDENTIALS));
    }
}
