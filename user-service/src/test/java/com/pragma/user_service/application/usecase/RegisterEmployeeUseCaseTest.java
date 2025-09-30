package com.pragma.user_service.application.usecase;

import com.pragma.user_service.application.ports.output.IUserPersistencePort;
import com.pragma.user_service.application.ports.output.IEmployeePersistencePort;
import com.pragma.user_service.application.ports.output.IGetTokenServicePort;
import com.pragma.user_service.application.ports.output.IRestaurantOwnerRestClientPort;
import com.pragma.user_service.domain.model.Employee;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.service.*;
import com.pragma.user_service.infraestructure.driver.rest.dto.RegisterEmployeeRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegisterEmployeeUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IEmployeePersistencePort employeePersistencePort;
    @Mock
    private IGetTokenServicePort tokenServicePort;
    @Mock
    private IRestaurantOwnerRestClientPort restaurantOwnerRestClientPort;
    @Mock
    private EmailValidationService emailValidationService;
    @Mock
    private FullNameValidationService fullNameValidationService;
    @Mock
    private PasswordValidationService passwordValidationService;
    @Mock
    private AgeValidationService ageValidationService;

    @InjectMocks
    private RegisterEmployeeUseCase registerEmployeeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterEmployee_Success() {
        RegisterEmployeeRequestDto dto = new RegisterEmployeeRequestDto();
        dto.setEmail("employee@example.com");
        dto.setUsername("employeeuser");
        dto.setPassword("Password123");
        dto.setFullName("Employee Name");
        dto.setTaxId("987654321");
        dto.setHireDate(LocalDate.of(2022, 1, 1));
        dto.setSalary(1500.0);
        dto.setRestaurantId(10L);
        dto.setPhone("3001234567");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));

        String bearerToken = "Bearer token";
        Long ownerId = 1L;

        User persistedUser = mock(User.class);
        when(persistedUser.getId()).thenReturn(2L);

        Employee employee = mock(Employee.class);

        when(emailValidationService.isValid(dto.getEmail())).thenReturn(true);
        when(fullNameValidationService.isValid(dto.getFullName())).thenReturn(true);
        when(passwordValidationService.isValid(dto.getPassword())).thenReturn(true);
        when(ageValidationService.isAdult(dto.getBirthDate())).thenReturn(true);
        when(userPersistencePort.existsByEmail(dto.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByUsername(dto.getUsername())).thenReturn(false);
        when(employeePersistencePort.existsByTaxId(dto.getTaxId())).thenReturn(false);
        when(tokenServicePort.extractUserId(bearerToken)).thenReturn(ownerId);
        when(restaurantOwnerRestClientPort.isOwnerOfRestaurant(ownerId, dto.getRestaurantId(), bearerToken)).thenReturn(true);
        when(userPersistencePort.saveUser(any(User.class), eq("Empleado"))).thenReturn(persistedUser);
        when(employeePersistencePort.saveEmployee(any(Employee.class))).thenReturn(employee);

        Employee result = registerEmployeeUseCase.registerEmployee(dto, bearerToken);

        assertNotNull(result);
        verify(employeePersistencePort).saveEmployee(any(Employee.class));
        verify(userPersistencePort).saveUser(any(User.class), eq("Empleado"));
    }
}

