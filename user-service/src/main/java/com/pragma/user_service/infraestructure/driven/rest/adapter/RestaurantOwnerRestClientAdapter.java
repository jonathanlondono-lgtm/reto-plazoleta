package com.pragma.user_service.infraestructure.driven.rest.adapter;


import com.pragma.user_service.application.ports.output.IRestaurantOwnerRestClientPort;
import com.pragma.user_service.infraestructure.driven.rest.dto.RestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantOwnerRestClientAdapter implements IRestaurantOwnerRestClientPort {

    private final RestTemplate restTemplate;

    @Override
    public boolean isOwnerOfRestaurant(Long ownerId, Long restaurantId, String token) {
        String url = "http://localhost:8081/api/owner/restaurants/" + ownerId;

        RestaurantResponseDto[] response = restTemplate.getForObject(
                url,
                RestaurantResponseDto[].class
        );

        if (response == null) {
            return false;
        }

        List<RestaurantResponseDto> restaurants = Arrays.asList(response);

        return restaurants.stream().anyMatch(r -> r.getId().equals(restaurantId));
    }
}
