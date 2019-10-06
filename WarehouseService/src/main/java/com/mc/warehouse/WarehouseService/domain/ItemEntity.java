package com.mc.warehouse.WarehouseService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "Items")
@AllArgsConstructor
@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class ItemEntity {
    public ItemEntity(@NotNull String name, @NotNull BigDecimal price, @NotNull Long amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long itemId;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long amount;
}
