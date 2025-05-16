package com.mycompany.supplier.service.impl;

import com.mycompany.supplier.entity.CustomerEntity;
import com.mycompany.supplier.repository.CustomerRepository;
import com.mycompany.supplier.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    public List<CustomerEntity> getAllCustomers() {
        return customerRepo.findAll();
    }

}