package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.ports.input.IEmployeeServicePort;
import com.pragma.user_service.infraestructure.driver.rest.dto.RegisterEmployeeRequestDto;
import com.pragma.user_service.infraestructure.driver.rest.dto.RegisterEmployeeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employee", description = "Endpoints for employee management")
public class EmployeeController {

    private final IEmployeeServicePort employeeServicePort;

    @Operation(summary = "Create a new Employee (HU-6)", description = "Allows an OWNER to create a new employee.")
    @PostMapping("/register")
    public ResponseEntity<RegisterEmployeeResponseDto> registerEmployee(
            @RequestBody RegisterEmployeeRequestDto requestDto,
            @RequestHeader("Authorization") String bearerToken
    ) {
        employeeServicePort.registerEmployee(requestDto, bearerToken);
        return ResponseEntity.ok(new RegisterEmployeeResponseDto("Empleado creado satisfactoriamente"));
    }
}
