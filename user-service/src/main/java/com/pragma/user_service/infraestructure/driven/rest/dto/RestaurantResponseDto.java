package com.pragma.user_service.infraestructure.driven.rest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String urlLogo;
}

