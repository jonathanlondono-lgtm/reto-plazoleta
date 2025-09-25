package com.pragma.user_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Role> roles;
    private LocalDate birthDate;
}
