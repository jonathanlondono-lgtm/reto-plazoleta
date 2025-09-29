package com.pragma.user_service.application.usecase;

import com.pragma.user_service.application.ports.input.IOwnerServicePort;
import com.pragma.user_service.application.ports.output.IUserPersistencePort;
import com.pragma.user_service.application.ports.output.IOwnerPersistencePort;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.model.Owner;
import com.pragma.user_service.domain.service.*;
import com.pragma.user_service.domain.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RegisterOwnerUseCase implements IOwnerServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IOwnerPersistencePort ownerPersistencePort;
    private final EmailValidationService emailValidationService;
    private final PhoneValidationService phoneValidationService;
    private final FullNameValidationService fullNameValidationService;
    private final PasswordValidationService passwordValidationService;
    private final AgeValidationService ageValidationService;


    @Override
    public Owner registerOwner(String email, String username, String password, String fullName, String taxId, String contactInfo, LocalDate birthDate) {
        // Validaciones obligatorias
        if (email == null || email.isEmpty()) throw new InvalidEmailFormatException();
        if (username == null || username.isEmpty()) throw new InvalidUserIdException();
        if (password == null || password.isEmpty()) throw new PasswordTooShortException();
        if (fullName == null || fullName.isEmpty()) throw new InvalidFullNameFormatException();
        if (taxId == null || taxId.isEmpty() || !taxId.matches("\\d+")) throw new InvalidUserIdException();
        if (contactInfo == null || contactInfo.isEmpty()) throw new InvalidPhoneFormatException();
        if (birthDate == null) throw new UserIsNotAdultException();

        // Validaciones de formato
        if (!emailValidationService.isValid(email)) throw new InvalidEmailFormatException();
        if (!phoneValidationService.isValid(contactInfo)) throw new InvalidPhoneFormatException();
        if (!fullNameValidationService.isValid(fullName)) throw new InvalidFullNameFormatException();
        if (!passwordValidationService.isValid(password)) throw new PasswordTooShortException();
        if (!ageValidationService.isAdult(birthDate)) throw new UserIsNotAdultException();

        // Verificar si el email o username ya existen
        if (userPersistencePort.existsByEmail(email)) throw new EmailAlreadyExistsException();
        if (userPersistencePort.existsByUsername(username)) throw new UsernameAlreadyExistsException();
        if (ownerPersistencePort.existsByTaxId(taxId)) throw new InvalidUserIdException();

        // Encriptar contraseña
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        // Crear y guardar el usuario
        User user = User.builder()
                .email(email)
                .username(username)
                .password(encryptedPassword)
                .birthDate(birthDate)
                .enabled(true)
                .build();
        User persistedUser = userPersistencePort.saveUser(user, "Propietario");

        // Crear el Owner usando el usuario persistido
        Owner owner = Owner.builder()
                .userId(persistedUser.getId())
                .user(persistedUser)
                .fullName(fullName)
                .taxId(taxId)
                .contactInfo(contactInfo)
                .build();

        // Guardar el Owner
        return ownerPersistencePort.saveOwner(owner);
    }
}
