package com.mc.warehouse.api.models;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long amount;
}
