package com.pragma.user_service.application.ports.input;

import com.pragma.user_service.domain.model.Owner;
import java.time.LocalDate;

public interface IOwnerServicePort {
    Owner registerOwner(String email, String username, String password, String fullName, String taxId, String contactInfo, LocalDate birthDate);
}
