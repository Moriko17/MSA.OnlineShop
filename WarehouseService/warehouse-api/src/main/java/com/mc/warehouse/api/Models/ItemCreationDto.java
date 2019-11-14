package com.mc.warehouse.api.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ItemCreationDto {
    private String name;
    private BigDecimal price;
    private Long amount;
}
