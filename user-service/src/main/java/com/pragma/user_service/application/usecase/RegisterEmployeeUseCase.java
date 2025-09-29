package com.pragma.user_service.application.usecase;

import com.pragma.user_service.application.ports.input.IEmployeeServicePort;
import com.pragma.user_service.application.ports.output.IUserPersistencePort;
import com.pragma.user_service.application.ports.output.IEmployeePersistencePort;
import com.pragma.user_service.application.ports.output.IGetTokenServicePort;
import com.pragma.user_service.application.ports.output.IRestaurantOwnerRestClientPort;
import com.pragma.user_service.domain.exception.*;
import com.pragma.user_service.domain.model.Employee;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.service.*;
import com.pragma.user_service.domain.util.ExceptionMessages;
import com.pragma.user_service.infraestructure.driver.rest.dto.RegisterEmployeeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterEmployeeUseCase implements IEmployeeServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEmployeePersistencePort employeePersistencePort;
    private final IGetTokenServicePort tokenServicePort;
    private final IRestaurantOwnerRestClientPort restaurantOwnerRestClientPort;
    private final EmailValidationService emailValidationService;
    private final FullNameValidationService fullNameValidationService;
    private final PasswordValidationService passwordValidationService;
    private final AgeValidationService ageValidationService;

    @Override
    public Employee registerEmployee(RegisterEmployeeRequestDto dto, String bearerToken) {
        validateRequiredFields(dto);
        validateFormatFields(dto);
        validateExistenceFields(dto);

        Long ownerId = tokenServicePort.extractUserId(bearerToken);
        boolean isOwner = restaurantOwnerRestClientPort.isOwnerOfRestaurant(ownerId, dto.getRestaurantId(), bearerToken);
        if (!isOwner) {
            throw new UnauthorizedOperationException(
                    ExceptionMessages.UNAUTHORIZED_OPERATION + " (restaurantId=" + dto.getRestaurantId() + ")"
            );
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());

        User user = User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(encryptedPassword)
                .birthDate(dto.getBirthDate())
                .enabled(true)
                .build();

        User persistedUser = userPersistencePort.saveUser(user, "Empleado");

        Employee employee = Employee.builder()
                .userId(persistedUser.getId())
                .fullName(dto.getFullName())
                .hireDate(dto.getHireDate())
                .salary(dto.getSalary())
                .restaurantId(dto.getRestaurantId())
                .phone(dto.getPhone())
                .taxId(dto.getTaxId())
                .build();

        return employeePersistencePort.saveEmployee(employee);
    }


    private void validateRequiredFields(RegisterEmployeeRequestDto dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) throw new InvalidEmailFormatException();
        if (dto.getUsername() == null || dto.getUsername().isEmpty()) throw new InvalidUserIdException();
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) throw new PasswordTooShortException();
        if (dto.getFullName() == null || dto.getFullName().isEmpty()) throw new InvalidFullNameFormatException();
        if (dto.getTaxId() == null || dto.getTaxId().isEmpty()) throw new InvalidUserIdException();
        if (dto.getHireDate() == null) throw new InvalidUserIdException();
        if (dto.getSalary() == null) throw new InvalidUserIdException();
        if (dto.getPhone() == null || dto.getPhone().isEmpty()) throw new InvalidPhoneFormatException();
        if (dto.getBirthDate() == null) throw new UserIsNotAdultException();
    }

    private void validateFormatFields(RegisterEmployeeRequestDto dto) {
        if (!emailValidationService.isValid(dto.getEmail())) throw new InvalidEmailFormatException();
        if (!fullNameValidationService.isValid(dto.getFullName())) throw new InvalidFullNameFormatException();
        if (!passwordValidationService.isValid(dto.getPassword())) throw new PasswordTooShortException();
        if (!ageValidationService.isAdult(dto.getBirthDate())) throw new UserIsNotAdultException();
    }

    private void validateExistenceFields(RegisterEmployeeRequestDto dto) {
        if (userPersistencePort.existsByEmail(dto.getEmail())) throw new EmailAlreadyExistsException();
        if (userPersistencePort.existsByUsername(dto.getUsername())) throw new UsernameAlreadyExistsException();
        if (employeePersistencePort.existsByTaxId(dto.getTaxId())) throw new InvalidUserIdException();
    }
}
