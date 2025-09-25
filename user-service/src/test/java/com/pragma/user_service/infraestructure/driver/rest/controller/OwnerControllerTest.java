package com.pragma.user_service.infraestructure.driver.rest.controller;

import com.pragma.user_service.application.usecase.RegisterOwnerUseCase;
import com.pragma.user_service.infraestructure.driver.rest.dto.OwnerRegisterRequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(OwnerController.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)
class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegisterOwnerUseCase registerOwnerUseCase;

    @Test
    void testRegisterOwner_success() throws Exception {
        OwnerRegisterRequestDto requestDto = new OwnerRegisterRequestDto();
        requestDto.setEmail("owner@example.com");
        requestDto.setUsername("owneruser");
        requestDto.setPassword("Password123@");
        requestDto.setFullName("Owner Name");
        requestDto.setTaxId("123456789");
        requestDto.setContactInfo("+573005698325");
        requestDto.setBirthDate(LocalDate.of(1990, 1, 1));

        when(registerOwnerUseCase.registerOwner(any(), any(), any(), any(), any(), any(), any()))
            .thenReturn(null);
        String json = "{\"email\":\"owner@example.com\",\"username\":\"owneruser\",\"password\":\"Password123@\",\"fullName\":\"Owner Name\",\"taxId\":\"123456789\",\"contactInfo\":\"+573005698325\",\"birthDate\":\"1990-01-01\"}";
        mockMvc.perform(post("/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Usuario creado satisfactoriamente"));
    }

}
