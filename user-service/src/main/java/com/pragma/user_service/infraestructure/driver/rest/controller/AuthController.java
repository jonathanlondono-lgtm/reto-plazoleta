package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.ports.input.IAuthServicePort;
import com.pragma.user_service.infraestructure.driver.rest.dto.LoginRequestDto;
import com.pragma.user_service.infraestructure.driver.rest.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints for auth management")

public class AuthController {
    private final IAuthServicePort authServicePort;

    @Operation(summary = "Login (HU-5)", description ="authentication for all roles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login exitoso, retorna el token JWT",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Credenciales inválidas o usuario no encontrado",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        String token = authServicePort.login(requestDto.getUsername(), requestDto.getPassword());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
