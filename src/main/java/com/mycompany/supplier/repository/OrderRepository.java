package com.mycompany.supplier.repository;

import com.mycompany.supplier.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomer_CustomerId(Long customerId);

}

