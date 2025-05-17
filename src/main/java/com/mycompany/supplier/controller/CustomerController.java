package com.mycompany.supplier.controller;

import com.mycompany.supplier.entity.CustomerEntity;
import com.mycompany.supplier.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPLIER')")
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PreAuthorize("hasRole('ADMIN','SUPPLIER')")
    @PostMapping("/add")
    public ResponseEntity<CustomerEntity> addSupplier(@Valid @RequestBody CustomerEntity customer){
        return customerService.addCustomer(customer);
    }

}
