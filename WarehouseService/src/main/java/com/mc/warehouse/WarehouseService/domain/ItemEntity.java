package com.mc.warehouse.WarehouseService.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "Items")
@Entity
public class ItemEntity {
    public ItemEntity() {}

    public ItemEntity(@NotNull String name, @NotNull BigDecimal price, @NotNull Long amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long amount;

    public Long getItemId() {
        return itemId;
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

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
