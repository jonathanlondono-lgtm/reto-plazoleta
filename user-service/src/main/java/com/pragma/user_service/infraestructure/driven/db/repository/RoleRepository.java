package com.pragma.user_service.infraestructure.driven.db.repository;

import com.pragma.user_service.infraestructure.driven.db.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);

    @Query("SELECT r FROM RoleEntity r WHERE TRIM(LOWER(r.name)) = TRIM(LOWER(:name))")
    Optional<RoleEntity> findByNameIgnoreCase(@Param("name") String name);

    @Query(value = "SELECT * FROM roles WHERE TRIM(LOWER(name)) = TRIM(LOWER(:name))", nativeQuery = true)
    Optional<RoleEntity> findByNameNative(@Param("name") String name);
}
