package com.mc.order.api.client;

import com.mc.order.api.models.ItemDto;
import com.mc.order.api.models.OrderDto;
import com.mc.order.api.models.OrderStatus;
import com.mc.order.api.service.OrderServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "orders")
public interface OrderServiceClient extends OrderServiceApi {
    @GetMapping
    List<OrderDto> getOrders();

    @GetMapping("/{id}")
    OrderDto getOrderById(@PathVariable(value = "id") Long id);

    @PostMapping("/{id}/item")
    OrderDto addItemToOrder(@PathVariable(value = "id") String id, @RequestBody ItemDto itemDto);

    @PutMapping("/{id}/status/{status}")
    OrderDto changeOrderStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "status") OrderStatus status);
}
