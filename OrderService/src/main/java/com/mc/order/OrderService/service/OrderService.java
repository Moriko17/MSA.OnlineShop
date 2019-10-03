package com.mc.order.OrderService.service;

import com.mc.order.OrderService.dataObjects.ItemDto;
import com.mc.order.OrderService.dataObjects.OfferDto;
import com.mc.order.OrderService.dataObjects.OrderDto;
import com.mc.order.OrderService.dataObjects.UserDetailsDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();
    OrderDto getOne(Long id);
    OrderDto addToOrder(Long id, ItemDto itemDto);
    OrderDto changeStatus(Long id, String status);
    OfferDto preparePayment(Long id, UserDetailsDto userDetailsDto);
}
