package com.mycompany.supplier.service;

import com.mycompany.supplier.entity.CustomerEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> getAllCustomers();
    ResponseEntity<CustomerEntity> addCustomer(CustomerEntity customer);
}
