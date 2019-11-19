package com.mc.order.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private OrderStatus status;
    private BigDecimal totalCost;
    private Integer totalAmount;
    private String userName;
    private List<ItemDto> items;
}
