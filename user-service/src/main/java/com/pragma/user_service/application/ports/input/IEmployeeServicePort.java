package com.pragma.user_service.application.ports.input;

import com.pragma.user_service.domain.model.Employee;
import com.pragma.user_service.infraestructure.driver.rest.dto.RegisterEmployeeRequestDto;

import java.time.LocalDate;

public interface IEmployeeServicePort {
    Employee registerEmployee(RegisterEmployeeRequestDto dto, String bearerToken);
}


