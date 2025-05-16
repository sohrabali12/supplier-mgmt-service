package com.mycompany.supplier.service.impl;

import com.mycompany.supplier.dto.ErrorDTO;
import com.mycompany.supplier.entity.OrderEntity;
import com.mycompany.supplier.entity.OrderItem;
import com.mycompany.supplier.exception.BusinessException;
import com.mycompany.supplier.repository.OrderItemRepository;
import com.mycompany.supplier.repository.OrderRepository;
import com.mycompany.supplier.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired private OrderItemRepository itemRepo;

    public OrderEntity getOrderById(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new BusinessException(List.of(
                        new ErrorDTO("ORDER_NOT_FOUND", "The order details not found"))));
    }

    public List<OrderItem> getOrderItems(Long orderId) {
        return itemRepo.findByOrder_OrderId(orderId);
    }
}