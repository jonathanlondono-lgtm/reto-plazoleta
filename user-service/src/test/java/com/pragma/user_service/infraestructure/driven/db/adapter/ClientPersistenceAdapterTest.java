package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.domain.model.Client;
import com.pragma.user_service.infraestructure.driven.db.entity.ClientEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.repository.ClientRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientPersistenceAdapterTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ClientPersistenceAdapter clientPersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExistsByTaxId_ReturnsTrue() {
        String taxId = "123456789";
        when(clientRepository.existsByTaxId(taxId)).thenReturn(true);
        boolean result = clientPersistenceAdapter.existsByTaxId(taxId);
        assertTrue(result);
        verify(clientRepository).existsByTaxId(taxId);
    }

    @Test
    void testExistsByTaxId_ReturnsFalse() {
        String taxId = "987654321";
        when(clientRepository.existsByTaxId(taxId)).thenReturn(false);
        boolean result = clientPersistenceAdapter.existsByTaxId(taxId);
        assertFalse(result);
        verify(clientRepository).existsByTaxId(taxId);
    }

    @Test
    void testSaveClient_Success() {
        Client client = Client.builder()
                .userId(1L)
                .fullName("Test Client")
                .phone("3001234567")
                .address("Calle 123")
                .taxId("123456789")
                .loyaltyPoints(0)
                .paymentInfo("cash")
                .build();
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("test@client.com")
                .username("testclient")
                .password("password")
                .enabled(true)
                .build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        doAnswer(invocation -> invocation.getArgument(0)).when(clientRepository).save(any(ClientEntity.class));

        Client result = clientPersistenceAdapter.saveClient(client);
        assertNotNull(result);
        assertEquals(client.getFullName(), result.getFullName());
        assertEquals(client.getPhone(), result.getPhone());
        assertEquals(client.getAddress(), result.getAddress());
        assertEquals(client.getTaxId(), result.getTaxId());
        assertEquals(client.getLoyaltyPoints(), result.getLoyaltyPoints());
        assertEquals(client.getPaymentInfo(), result.getPaymentInfo());
        verify(userRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).save(any(ClientEntity.class));
    }
}
