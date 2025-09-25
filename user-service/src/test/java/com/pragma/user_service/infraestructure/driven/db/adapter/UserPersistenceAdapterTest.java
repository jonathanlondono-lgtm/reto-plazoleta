package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.infraestructure.driven.db.entity.RoleEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.mapper.UserEntityMapper;
import com.pragma.user_service.infraestructure.driven.db.repository.RoleRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserPersistenceAdapterTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser_assignsRoleAndSavesUser() {

        User user = mock(User.class);
        RoleEntity roleEntity = mock(RoleEntity.class);
        UserEntity userEntity = mock(UserEntity.class);
        UserEntity savedEntity = mock(UserEntity.class);
        when(roleRepository.findByNameIgnoreCase("Propietario")).thenReturn(Optional.of(roleEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedEntity);

        mockStatic(UserEntityMapper.class);
        when(UserEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(UserEntityMapper.toDomain(savedEntity)).thenReturn(user);

        User result = adapter.saveUser(user);

        assertNotNull(result);
        verify(roleRepository).findByNameIgnoreCase("Propietario");
        verify(userRepository).save(userEntity);
        verify(userEntity).setRoles(Collections.singletonList(roleEntity));
    }

    @Test
    void testExistsByEmail_delegatesToRepository() {

        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean result = adapter.existsByEmail(email);

        assertTrue(result);
        verify(userRepository).existsByEmail(email);
    }

    @Test
    void testExistsByUsername_delegatesToRepository() {

        String username = "user1";
        when(userRepository.existsByUsername(username)).thenReturn(true);

        boolean result = adapter.existsByUsername(username);

        assertTrue(result);
        verify(userRepository).existsByUsername(username);
    }
}

