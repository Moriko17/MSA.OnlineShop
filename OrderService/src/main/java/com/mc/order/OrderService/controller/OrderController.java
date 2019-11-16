package com.mc.order.OrderService.controller;

import com.mc.order.OrderService.dataObjects.ItemDto;
import com.mc.order.OrderService.dataObjects.OrderDto;
import com.mc.order.OrderService.domain.OrderStatus;
import com.mc.order.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/{id}/item")
    public OrderDto addItemToOrder(@PathVariable String id, @RequestBody ItemDto itemDto) {
        return orderService.addItemToOrder(id, itemDto);
    }

    @PutMapping("/{id}/status/{status}")
    public OrderDto changeOrderStatus(@PathVariable Long id, @PathVariable OrderStatus status) {
        return orderService.changeOrderStatus(id, status);
    }
}
