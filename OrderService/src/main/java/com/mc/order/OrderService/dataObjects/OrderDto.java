package com.mc.order.OrderService.dataObjects;

import com.mc.order.OrderService.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private OrderStatus status;
    private BigDecimal totalCost;
    private Integer totalAmount;
    private String userName;
    private List<ItemDto> items;
}
