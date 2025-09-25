package com.pragma.user_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    private Long userId;
    private String fullName;
    private String taxId;
    private String contactInfo;
    private User user;
}
