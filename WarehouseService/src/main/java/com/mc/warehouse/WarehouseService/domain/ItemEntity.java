package com.mc.warehouse.WarehouseService.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class ItemEntity {
    public ItemEntity() {}

    public ItemEntity(@NotNull BigDecimal price, @NotNull String name, @NotNull Long amount) {
        this.price = price;
        this.name = name;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String name;

    @NotNull
    private Long amount;

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
