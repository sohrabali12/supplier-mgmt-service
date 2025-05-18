package com.mycompany.supplier.controller;

import com.mycompany.supplier.dto.OrderDTO;
import com.mycompany.supplier.dto.OrderItemDTO;
import com.mycompany.supplier.entity.OrderEntity;
import com.mycompany.supplier.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired private OrderService orderService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPPLIER','CUSTOMER')")
    @PostMapping("/add")
    public ResponseEntity<OrderDTO> addOrder(@Valid @RequestBody OrderEntity order){
        return orderService.addOrder(order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'SUPPLIER')")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'SUPPLIER')")
    public ResponseEntity<List<OrderItemDTO>> getOrderItems(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderItems(id));
    }

}
