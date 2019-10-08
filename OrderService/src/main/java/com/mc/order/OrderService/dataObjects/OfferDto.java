package com.mc.order.OrderService.dataObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OfferDto {
    private String userName;
    private Long orderId;
}
