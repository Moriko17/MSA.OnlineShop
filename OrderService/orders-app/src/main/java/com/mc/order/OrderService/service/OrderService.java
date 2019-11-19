package com.mc.order.OrderService.service;

import com.mc.order.api.models.ItemDto;
import com.mc.order.api.models.OrderDto;
import com.mc.order.api.models.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders();
    OrderDto getOrderById(Long id);
    OrderDto addItemToOrder(String id, ItemDto itemDto);
    OrderDto changeOrderStatus(Long id, OrderStatus status);
}
