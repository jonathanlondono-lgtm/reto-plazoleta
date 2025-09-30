package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.domain.model.Employee;
import com.pragma.user_service.infraestructure.driven.db.entity.EmployeeEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.repository.EmployeeRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeePersistenceAdapterTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private EmployeePersistenceAdapter employeePersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExistsByTaxId_ReturnsTrue() {
        String taxId = "123456789";
        when(employeeRepository.existsByTaxId(taxId)).thenReturn(true);
        boolean result = employeePersistenceAdapter.existsByTaxId(taxId);
        assertTrue(result);
        verify(employeeRepository).existsByTaxId(taxId);
    }

    @Test
    void testExistsByTaxId_ReturnsFalse() {
        String taxId = "987654321";
        when(employeeRepository.existsByTaxId(taxId)).thenReturn(false);
        boolean result = employeePersistenceAdapter.existsByTaxId(taxId);
        assertFalse(result);
        verify(employeeRepository).existsByTaxId(taxId);
    }

    @Test
    void testSaveEmployee_Success() {
        Employee employee = Employee.builder()
                .userId(1L)
                .fullName("Empleado Test")
                .hireDate(LocalDate.of(2024, 1, 1))
                .salary(2500.0)
                .restaurantId(10L)
                .phone("3001234567")
                .taxId("123456789")
                .build();
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("empleado@test.com")
                .username("empleadotest")
                .password("password")
                .enabled(true)
                .build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        doAnswer(invocation -> invocation.getArgument(0)).when(employeeRepository).save(any(EmployeeEntity.class));

        Employee result = employeePersistenceAdapter.saveEmployee(employee);
        assertNotNull(result);
        assertEquals(employee.getFullName(), result.getFullName());
        assertEquals(employee.getHireDate(), result.getHireDate());
        assertEquals(employee.getSalary(), result.getSalary());
        assertEquals(employee.getRestaurantId(), result.getRestaurantId());
        assertEquals(employee.getPhone(), result.getPhone());
        assertEquals(employee.getTaxId(), result.getTaxId());
        verify(userRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
    }
}
