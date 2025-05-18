package com.mycompany.supplier.convertor;

import com.mycompany.supplier.dto.OrderDTO;
import com.mycompany.supplier.dto.OrderItemDTO;
import com.mycompany.supplier.entity.OrderEntity;
import com.mycompany.supplier.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConvertor {

    public OrderDTO convertOrderToDTO(OrderEntity order){

        List<OrderItemDTO> items = order.getItems().stream().map(
                item -> {
                    OrderItemDTO itemDto = new OrderItemDTO();
                    itemDto.setId(item.getOrderItemId());
                    itemDto.setProductName(item.getProductName());
                    itemDto.setQuantity(item.getQuantity());
                    return itemDto;
                }
        ).collect(Collectors.toList());

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getOrderId());
        orderDTO.setOrderItems(items);
        orderDTO.setOrderDate(order.getOrderDate().toString());
        return orderDTO;
    }

    public List<OrderItemDTO> convertItemEntityToDTO(List<OrderItem> items){
        return items.stream().map(
                item -> {
                    OrderItemDTO itemDto = new OrderItemDTO();
                    itemDto.setId(item.getOrderItemId());
                    itemDto.setProductName(item.getProductName());
                    itemDto.setQuantity(item.getQuantity());
                    return itemDto;
                }
        ).collect(Collectors.toList());
    }
}
