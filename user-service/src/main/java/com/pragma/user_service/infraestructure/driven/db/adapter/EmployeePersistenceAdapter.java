package com.pragma.user_service.infraestructure.driven.db.adapter;

import com.pragma.user_service.application.ports.output.IEmployeePersistencePort;
import com.pragma.user_service.domain.model.Employee;
import com.pragma.user_service.infraestructure.driven.db.entity.EmployeeEntity;
import com.pragma.user_service.infraestructure.driven.db.entity.UserEntity;
import com.pragma.user_service.infraestructure.driven.db.mapper.EmployeeEntityMapper;
import com.pragma.user_service.infraestructure.driven.db.repository.EmployeeRepository;
import com.pragma.user_service.infraestructure.driven.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements IEmployeePersistencePort {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Override
    public boolean existsByTaxId(String taxId) {
        return employeeRepository.existsByTaxId(taxId);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        UserEntity persistedUser = userRepository.findById(employee.getUserId()).orElseThrow();
        EmployeeEntity entity = EmployeeEntityMapper.toEntity(employee, persistedUser);
        EmployeeEntity saved = employeeRepository.save(entity);
        return EmployeeEntityMapper.toDomain(saved);
    }
}
