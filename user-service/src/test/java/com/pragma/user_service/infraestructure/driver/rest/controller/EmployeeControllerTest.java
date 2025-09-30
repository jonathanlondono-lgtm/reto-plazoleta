package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.ports.input.IEmployeeServicePort;
import com.pragma.user_service.infraestructure.driver.rest.dto.RegisterEmployeeRequestDto;
import com.pragma.user_service.infraestructure.driver.rest.dto.RegisterEmployeeResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {
    @Mock
    private IEmployeeServicePort employeeServicePort;
    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterEmployee_ReturnsSuccessResponse() {
        RegisterEmployeeRequestDto requestDto = new RegisterEmployeeRequestDto();
        String bearerToken = "Bearer token";
        doNothing().when(employeeServicePort).registerEmployee(requestDto, bearerToken);

        ResponseEntity<RegisterEmployeeResponseDto> response = employeeController.registerEmployee(requestDto, bearerToken);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Empleado creado satisfactoriamente", response.getBody().getMessage());
        verify(employeeServicePort, times(1)).registerEmployee(requestDto, bearerToken);
    }
}

