package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.ports.input.IClientServicePort;
import com.pragma.user_service.infraestructure.driver.rest.dto.ClientRegisterRequestDto;
import com.pragma.user_service.infraestructure.driver.rest.dto.ClientRegisterResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {
    @Mock
    private IClientServicePort clientServicePort;
    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterClient_ReturnsSuccessResponse() {
        ClientRegisterRequestDto requestDto = new ClientRegisterRequestDto();
        doNothing().when(clientServicePort).registerClient(requestDto);

        ResponseEntity<ClientRegisterResponseDto> response = clientController.registerClient(requestDto);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cliente creado satisfactoriamente", response.getBody().getMessage());
        verify(clientServicePort, times(1)).registerClient(requestDto);
    }
}

