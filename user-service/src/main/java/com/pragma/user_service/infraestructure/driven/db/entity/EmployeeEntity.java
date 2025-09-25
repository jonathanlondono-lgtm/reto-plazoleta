package com.pragma.user_service.infraestructure.driven.db.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "salary")
    private BigDecimal salary;
}

