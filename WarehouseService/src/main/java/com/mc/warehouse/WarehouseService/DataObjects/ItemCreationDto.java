package com.mc.warehouse.WarehouseService.DataObjects;

import java.math.BigDecimal;

public class ItemCreationDto {
    private String name;
    private BigDecimal price;
    private Long amount;

    public ItemCreationDto(String name, BigDecimal price, Long amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getAmount() {
        return amount;
    }
}
