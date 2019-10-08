package com.mc.order.OrderService.dataObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDto {
    private Long itemId;
    private Integer amount;
    private String userName;
}
