package com.mycompany.supplier.service.impl;

import com.mycompany.supplier.convertor.OrderConvertor;
import com.mycompany.supplier.dto.ErrorDTO;
import com.mycompany.supplier.dto.OrderDTO;
import com.mycompany.supplier.dto.OrderItemDTO;
import com.mycompany.supplier.entity.OrderEntity;
import com.mycompany.supplier.entity.OrderItem;
import com.mycompany.supplier.exception.BusinessException;
import com.mycompany.supplier.repository.OrderItemRepository;
import com.mycompany.supplier.repository.OrderRepository;
import com.mycompany.supplier.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderItemRepository itemRepo;
    @Autowired
    private OrderConvertor orderConvertor;

    public OrderDTO getOrderById(Long orderId) {
        OrderEntity orderEntity = orderRepo.findById(orderId)
                .orElseThrow(() -> new BusinessException(List.of(
                        new ErrorDTO("ORDER_NOT_FOUND", "The order details not found"))));
        return orderConvertor.convertOrderToDTO(orderEntity);
    }

    public List<OrderItemDTO> getOrderItems(Long orderId) {
        List<OrderItem> items = itemRepo.findByOrder_OrderId(orderId);
        return orderConvertor.convertItemEntityToDTO(items);
    }

    @Override
    public ResponseEntity<OrderDTO> addOrder(OrderEntity order) {
        order.setOrderDate(LocalDate.now());
        order.setStatus("Success");
        //Setting the Order detail in each item to save the item level details explicitly
        for(OrderItem item : order.getItems()){
            item.setOrder(order);
        }
        OrderEntity savedOrderEntity = orderRepo.save(order);
        return new ResponseEntity<>(orderConvertor.convertOrderToDTO(savedOrderEntity),HttpStatus.CREATED);
    }
}