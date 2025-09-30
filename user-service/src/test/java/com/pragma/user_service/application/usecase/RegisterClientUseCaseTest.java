package com.pragma.user_service.application.usecase;

import com.pragma.user_service.application.ports.output.IUserPersistencePort;
import com.pragma.user_service.application.ports.output.IClientPersistencePort;
import com.pragma.user_service.domain.model.Client;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.service.*;
import com.pragma.user_service.infraestructure.driver.rest.dto.ClientRegisterRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegisterClientUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IClientPersistencePort clientPersistencePort;
    @Mock
    private EmailValidationService emailValidationService;
    @Mock
    private FullNameValidationService fullNameValidationService;
    @Mock
    private PasswordValidationService passwordValidationService;
    @Mock
    private AgeValidationService ageValidationService;

    @InjectMocks
    private RegisterClientUseCase registerClientUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterClient_Success() {
        ClientRegisterRequestDto dto = new ClientRegisterRequestDto();
        dto.setEmail("client@example.com");
        dto.setUsername("clientuser");
        dto.setPassword("Password123");
        dto.setFullName("Client Name");
        dto.setTaxId("123456789");
        dto.setPhone("3001234567");
        dto.setBirthDate(LocalDate.of(1995, 1, 1));

        User persistedUser = mock(User.class);
        when(persistedUser.getId()).thenReturn(2L);

        Client client = mock(Client.class);

        when(emailValidationService.isValid(dto.getEmail())).thenReturn(true);
        when(fullNameValidationService.isValid(dto.getFullName())).thenReturn(true);
        when(passwordValidationService.isValid(dto.getPassword())).thenReturn(true);
        when(ageValidationService.isAdult(dto.getBirthDate())).thenReturn(true);
        when(userPersistencePort.existsByEmail(dto.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByUsername(dto.getUsername())).thenReturn(false);
        when(clientPersistencePort.existsByTaxId(dto.getTaxId())).thenReturn(false);
        when(userPersistencePort.saveUser(any(User.class), eq("Cliente"))).thenReturn(persistedUser);
        when(clientPersistencePort.saveClient(any(Client.class))).thenReturn(client);

        Client result = registerClientUseCase.registerClient(dto);

        assertNotNull(result);
        verify(clientPersistencePort).saveClient(any(Client.class));
        verify(userPersistencePort).saveUser(any(User.class), eq("Cliente"));
    }
}

