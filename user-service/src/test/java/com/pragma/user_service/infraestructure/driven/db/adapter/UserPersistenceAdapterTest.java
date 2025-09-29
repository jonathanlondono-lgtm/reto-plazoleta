package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.mapper.UserEntityMapper;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserPersistenceAdapterTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsername_UserExists() {
        // Arrange
        String username = "user1";
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(entity));
        when(userEntityMapper.toDomain(entity)).thenReturn(user);
        // Act
        User result = userPersistenceAdapter.findByUsername(username);
        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    void testFindByUsername_UserNotExists() {
        // Arrange
        String username = "user2";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        // Act
        User result = userPersistenceAdapter.findByUsername(username);
        // Assert
        assertNull(result);
    }
}

