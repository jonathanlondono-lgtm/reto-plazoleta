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

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "taxid", unique = true, length = 50)
    private String taxId;
}
