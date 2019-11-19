package com.mc.warehouse.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreationDto {
    private String name;
    private BigDecimal price;
    private Long amount;
}
