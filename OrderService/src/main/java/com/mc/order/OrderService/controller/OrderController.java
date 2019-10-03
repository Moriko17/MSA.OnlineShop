package com.mc.order.OrderService.controller;


import com.mc.order.OrderService.dataObjects.ItemDto;
import com.mc.order.OrderService.dataObjects.OfferDto;
import com.mc.order.OrderService.dataObjects.OrderDto;
import com.mc.order.OrderService.dataObjects.UserDetailsDto;
import com.mc.order.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api2/orders")
public class OrderController {

    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getOne(Long id) {
        return orderService.getOne(id);
    }

    @PostMapping("/{id}/add")
    public OrderDto addToOrder(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        return orderService.addToOrder(id, itemDto);
    }

    @PostMapping("//add")
    public OrderDto addToOrder(@RequestBody ItemDto itemDto) {
        return orderService.addToOrder(null, itemDto);
    }

    @PutMapping("/{id}/status/{status}")
    public OrderDto changeStatus(@PathVariable Long id, @PathVariable String status) {
        return orderService.changeStatus(id, status);
    }

    @PutMapping("/{id}/pay")
    public OfferDto preparePayment(@PathVariable Long id, @RequestBody UserDetailsDto userDetailsDto) {
        return orderService.preparePayment(id, userDetailsDto);
    }
}
