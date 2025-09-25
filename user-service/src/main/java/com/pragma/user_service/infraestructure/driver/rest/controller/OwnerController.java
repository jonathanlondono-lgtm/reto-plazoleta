package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.usecase.RegisterOwnerUseCase;
import com.pragma.user_service.infraestructure.driver.rest.dto.OwnerRegisterRequestDto;
import com.pragma.user_service.infraestructure.driver.rest.dto.OwnerRegisterResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Owners", description = "Operaciones sobre propietarios")
@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final RegisterOwnerUseCase registerOwnerUseCase;

    @Operation(summary = "Registrar propietario", description = "Crea un nuevo propietario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Propietario creado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<OwnerRegisterResponseDto> registerOwner(@RequestBody OwnerRegisterRequestDto requestDto) {
        registerOwnerUseCase.registerOwner(
            requestDto.getEmail(),
            requestDto.getUsername(),
            requestDto.getPassword(),
            requestDto.getFullName(),
            requestDto.getTaxId(),
            requestDto.getContactInfo(),
            requestDto.getBirthDate()
        );
        return ResponseEntity.ok(new OwnerRegisterResponseDto("Usuario creado satisfactoriamente"));
    }
}
