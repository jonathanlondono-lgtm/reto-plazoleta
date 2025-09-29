package com.pragma.user_service.application.ports.output;


public interface IRestaurantOwnerRestClientPort {
    boolean isOwnerOfRestaurant(Long ownerId, Long restaurantId, String token);
}

