package com.mc.warehouse.WarehouseService.DataObjects;
import java.math.BigDecimal;

public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long amount;

    public ItemDto(Long id, String name, BigDecimal price, Long amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
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
