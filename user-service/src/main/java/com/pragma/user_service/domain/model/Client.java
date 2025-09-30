package com.pragma.user_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long userId;
    private String fullName;
    private String phone;
    private String address;
    private Integer loyaltyPoints;
    private String paymentInfo;
    private String taxId;
    private User user;


}
