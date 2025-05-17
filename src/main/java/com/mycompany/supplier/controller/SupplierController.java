package com.mycompany.supplier.controller;

import com.mycompany.supplier.entity.SupplierEntity;
import com.mycompany.supplier.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/by-category")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<SupplierEntity>> getSuppliersByCategory(@RequestParam String category) {
        return ResponseEntity.ok(supplierService.getSuppliersByCategory(category));
    }

    @PutMapping("/{supplierId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SupplierEntity> updateSupplier(@Valid @RequestBody SupplierEntity supplier, @PathVariable Long supplierId){
        return supplierService.updateSupplier(supplier);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<SupplierEntity> addSupplier(@Valid @RequestBody SupplierEntity supplier){
        return supplierService.addSupplier(supplier);
    }
}
