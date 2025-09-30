package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.ports.input.IClientServicePort;
import com.pragma.user_service.infraestructure.driver.rest.dto.ClientRegisterRequestDto;
import com.pragma.user_service.infraestructure.driver.rest.dto.ClientRegisterResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Client", description = "Endpoints for client management")
public class ClientController {

    private final IClientServicePort clientServicePort;

    @Operation(summary = "Registrar nuevo cliente", description = "Permite registrar un nuevo cliente.")
    @PostMapping("/register")
    public ResponseEntity<ClientRegisterResponseDto> registerClient(@RequestBody ClientRegisterRequestDto requestDto) {
        clientServicePort.registerClient(requestDto);
        return ResponseEntity.ok(new ClientRegisterResponseDto("Cliente creado satisfactoriamente"));
    }
}
