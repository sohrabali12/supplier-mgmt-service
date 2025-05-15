package com.mycompany.supplier.service;

import com.mycompany.supplier.entity.SupplierEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SupplierService {
    List<SupplierEntity> getSuppliersByCategory(String categoryName);
    ResponseEntity<SupplierEntity> updateSupplier(SupplierEntity supplier);
    ResponseEntity<SupplierEntity> addSupplier(SupplierEntity supplier);
}
