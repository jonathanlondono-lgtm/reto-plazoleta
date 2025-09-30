package com.pragma.user_service.application.usecase;

import com.pragma.user_service.application.ports.input.IClientServicePort;
import com.pragma.user_service.application.ports.output.IUserPersistencePort;
import com.pragma.user_service.application.ports.output.IClientPersistencePort;
import com.pragma.user_service.domain.exception.*;
import com.pragma.user_service.domain.model.Client;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.service.*;
import com.pragma.user_service.domain.util.ExceptionMessages;
import com.pragma.user_service.infraestructure.driver.rest.dto.ClientRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterClientUseCase implements IClientServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IClientPersistencePort clientPersistencePort;
    private final EmailValidationService emailValidationService;
    private final FullNameValidationService fullNameValidationService;
    private final PasswordValidationService passwordValidationService;
    private final AgeValidationService ageValidationService;

    @Override
    public Client registerClient(ClientRegisterRequestDto dto) {
        validateRequiredFields(dto);
        validateFormatFields(dto);
        validateExistenceFields(dto);

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());

        User user = User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(encryptedPassword)
                .birthDate(dto.getBirthDate())
                .enabled(true)
                .build();

        User persistedUser = userPersistencePort.saveUser(user, "Cliente");

        Client client = Client.builder()
                .userId(persistedUser.getId())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .taxId(dto.getTaxId())
                .loyaltyPoints(0)
                .paymentInfo("cash")
                .build();

        return clientPersistencePort.saveClient(client);
    }

    private void validateRequiredFields(ClientRegisterRequestDto dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) throw new InvalidEmailFormatException();
        if (dto.getUsername() == null || dto.getUsername().isEmpty()) throw new InvalidUserIdException();
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) throw new PasswordTooShortException();
        if (dto.getFullName() == null || dto.getFullName().isEmpty()) throw new InvalidFullNameFormatException();
        if (dto.getTaxId() == null || dto.getTaxId().isEmpty()) throw new InvalidUserIdException();
        if (dto.getPhone() == null || dto.getPhone().isEmpty()) throw new InvalidPhoneFormatException();
        if (dto.getBirthDate() == null) throw new UserIsNotAdultException();
    }

    private void validateFormatFields(ClientRegisterRequestDto dto) {
        if (!emailValidationService.isValid(dto.getEmail())) throw new InvalidEmailFormatException();
        if (!fullNameValidationService.isValid(dto.getFullName())) throw new InvalidFullNameFormatException();
        if (!passwordValidationService.isValid(dto.getPassword())) throw new PasswordTooShortException();
        if (!ageValidationService.isAdult(dto.getBirthDate())) throw new UserIsNotAdultException();
    }

    private void validateExistenceFields(ClientRegisterRequestDto dto) {
        if (userPersistencePort.existsByEmail(dto.getEmail())) throw new EmailAlreadyExistsException();
        if (userPersistencePort.existsByUsername(dto.getUsername())) throw new UsernameAlreadyExistsException();
        if (clientPersistencePort.existsByTaxId(dto.getTaxId())) throw new InvalidUserIdException();
    }
}
