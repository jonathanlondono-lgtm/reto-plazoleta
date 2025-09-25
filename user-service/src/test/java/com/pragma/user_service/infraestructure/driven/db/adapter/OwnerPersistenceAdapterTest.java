package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.domain.model.Owner;
import com.pragma.user_service.infraestructure.driven.db.entity.OwnerEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.mapper.OwnerEntityMapper;
import com.pragma.user_service.infraestructure.driven.db.repository.OwnerRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OwnerPersistenceAdapterTest {
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private OwnerPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOwner_findsUserAndSavesOwner() {

        Owner owner = mock(Owner.class);
        UserEntity userEntity = mock(UserEntity.class);
        OwnerEntity ownerEntity = mock(OwnerEntity.class);
        OwnerEntity savedEntity = mock(OwnerEntity.class);
        when(owner.getUserId()).thenReturn(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(ownerRepository.save(any(OwnerEntity.class))).thenReturn(savedEntity);

        mockStatic(OwnerEntityMapper.class);
        when(OwnerEntityMapper.toEntity(owner, userEntity)).thenReturn(ownerEntity);
        when(OwnerEntityMapper.toDomain(savedEntity)).thenReturn(owner);

        Owner result = adapter.saveOwner(owner);

        assertNotNull(result);
        verify(userRepository).findById(1L);
        verify(ownerRepository).save(ownerEntity);
    }

    @Test
    void testExistsByTaxId_delegatesToRepository() {

        String taxId = "123456789";
        when(ownerRepository.existsByTaxId(taxId)).thenReturn(true);

        boolean result = adapter.existsByTaxId(taxId);

        assertTrue(result);
        verify(ownerRepository).existsByTaxId(taxId);
    }
}

