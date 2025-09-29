package com.pragma.user_service.application.ports.output;

public interface IGetTokenServicePort {
    Long extractUserId(String bearerToken);

}
