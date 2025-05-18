package com.mycompany.supplier.service;

import com.mycompany.supplier.dto.OrderDTO;
import com.mycompany.supplier.dto.OrderItemDTO;
import com.mycompany.supplier.entity.OrderEntity;
import com.mycompany.supplier.entity.OrderItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    OrderDTO getOrderById(Long orderId);
    List<OrderItemDTO> getOrderItems(Long orderId);
    ResponseEntity<OrderDTO> addOrder(OrderEntity order);
}
