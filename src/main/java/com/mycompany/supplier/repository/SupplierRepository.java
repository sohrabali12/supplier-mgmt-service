package com.mycompany.supplier.repository;

import com.mycompany.supplier.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
    List<SupplierEntity> findByCategory_Name(String categoryName);
}
