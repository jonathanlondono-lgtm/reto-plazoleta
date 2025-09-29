package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.ports.input.IRoleServicePort;
import com.pragma.user_service.domain.model.Role;
import com.pragma.user_service.infraestructure.driver.rest.dto.RoleResponseDto;
import com.pragma.user_service.infraestructure.driver.rest.mapper.RoleRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleServicePort roleServicePort;

    @GetMapping("/{userId}")
    public List<RoleResponseDto> getRolesByUserId(@PathVariable Long userId) {
        List<Role> roles = roleServicePort.getRolesByUserId(userId);
        return roles.stream().map(RoleRestMapper::toResponseDto).collect(Collectors.toList());
    }
}


