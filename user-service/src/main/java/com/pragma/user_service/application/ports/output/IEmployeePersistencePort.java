package com.pragma.user_service.application.ports.output;

import com.pragma.user_service.domain.model.Employee;

public interface IEmployeePersistencePort {
    boolean existsByTaxId(String taxId);
    Employee saveEmployee(Employee employee);
}

