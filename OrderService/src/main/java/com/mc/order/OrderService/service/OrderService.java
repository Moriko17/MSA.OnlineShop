package com.mc.order.OrderService.service;

import com.mc.order.OrderService.dataObjects.ItemDto;
import com.mc.order.OrderService.dataObjects.OrderDto;
import com.mc.order.OrderService.domain.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders();
    OrderDto getOrderById(Long id);
    OrderDto addItemToOrder(String id, ItemDto itemDto);
    OrderDto changeOrderStatus(Long id, OrderStatus status);
}
