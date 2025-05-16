package com.mycompany.supplier.service;

import com.mycompany.supplier.entity.OrderEntity;
import com.mycompany.supplier.entity.OrderItem;

import java.util.List;

public interface OrderService {
    OrderEntity getOrderById(Long orderId);
    List<OrderItem> getOrderItems(Long orderId);
}
