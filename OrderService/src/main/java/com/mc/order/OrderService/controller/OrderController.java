package com.mc.order.OrderService.controller;

import com.mc.order.OrderService.dataObjects.ItemDto;
import com.mc.order.OrderService.dataObjects.OrderDto;
import com.mc.order.OrderService.dataObjects.UserDetailsDto;
import com.mc.order.OrderService.domain.OrdersStatus;
import com.mc.order.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
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

    @PostMapping("/{id}/add")
    public OrderDto addItemToOrder(@PathVariable String id, @RequestBody ItemDto itemDto) {
        return orderService.addItemToOrder(id, itemDto);
    }

    @PutMapping("/{id}/status/{status}")
    public OrderDto changeOrdersStatus(@PathVariable Long id, @PathVariable OrdersStatus status) {
        return orderService.changeOrdersStatus(id, status);
    }

    @PutMapping("/{id}/checkout")
    public OrderDto checkoutOrder(@PathVariable Long id, @RequestBody UserDetailsDto userDetailsDto) {
        return orderService.checkoutOrder(id, userDetailsDto);
    }
}
