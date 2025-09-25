package com.pragma.user_service.application.usecase;

import com.pragma.user_service.domain.model.Owner;
import com.pragma.user_service.application.ports.output.IOwnerPersistencePort;
import com.pragma.user_service.application.ports.output.IUserPersistencePort;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegisterOwnerUseCaseTest {
    @Mock
    private IOwnerPersistencePort ownerPersistencePort;
    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private EmailValidationService emailValidationService;
    @Mock
    private AgeValidationService ageValidationService;
    @Mock
    private FullNameValidationService fullNameValidationService;
    @Mock
    private PhoneValidationService phoneValidationService;
    @Mock
    private PasswordValidationService passwordValidationService;

    @InjectMocks
    private RegisterOwnerUseCase registerOwnerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterOwner_Success() {
        // Arrange
        String email = "owner@example.com";
        String username = "owneruser";
        String password = "Password123";
        String fullName = "Owner Name";
        String taxId = "123456789";
        String contactInfo = "3001234567";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        Owner owner = mock(Owner.class);
        User persistedUser = mock(User.class);
        when(persistedUser.getId()).thenReturn(1L);
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(persistedUser);
        when(ownerPersistencePort.saveOwner(any(Owner.class))).thenReturn(owner);
        when(userPersistencePort.existsByEmail(email)).thenReturn(false);
        when(userPersistencePort.existsByUsername(username)).thenReturn(false);
        when(emailValidationService.isValid(email)).thenReturn(true);
        when(passwordValidationService.isValid(password)).thenReturn(true);
        when(fullNameValidationService.isValid(fullName)).thenReturn(true);
        when(phoneValidationService.isValid(contactInfo)).thenReturn(true);
        when(ageValidationService.isAdult(birthDate)).thenReturn(true);
        when(ownerPersistencePort.existsByTaxId(taxId)).thenReturn(false);

        // Act
        Owner result = registerOwnerUseCase.registerOwner(email, username, password, fullName, taxId, contactInfo, birthDate);

        // Assert
        assertNotNull(result);
        verify(ownerPersistencePort).saveOwner(any(Owner.class));
    }


}
