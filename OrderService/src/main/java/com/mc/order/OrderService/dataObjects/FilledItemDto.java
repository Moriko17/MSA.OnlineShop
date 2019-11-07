package com.mc.order.OrderService.dataObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilledItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer amount;
}
