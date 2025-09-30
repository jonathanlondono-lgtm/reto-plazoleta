package com.pragma.user_service.application.ports.input;

import com.pragma.user_service.domain.model.Client;
import com.pragma.user_service.infraestructure.driver.rest.dto.ClientRegisterRequestDto;

public interface IClientServicePort {
    Client registerClient(ClientRegisterRequestDto clientRequestDto);
}
