package com.mycompany.supplier.repository;

import com.mycompany.supplier.constant.ERole;
import com.mycompany.supplier.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole name);
}