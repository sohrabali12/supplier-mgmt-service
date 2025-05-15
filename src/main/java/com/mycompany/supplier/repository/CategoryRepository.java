package com.mycompany.supplier.repository;

import com.mycompany.supplier.entity.CategoryEntity;
import com.mycompany.supplier.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<UserEntity> findByName(String name);
    Boolean existsByName(String name);
}