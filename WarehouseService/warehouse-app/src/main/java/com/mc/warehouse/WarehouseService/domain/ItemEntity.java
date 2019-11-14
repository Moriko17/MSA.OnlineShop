package com.mc.warehouse.WarehouseService.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "Items")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ItemEntity {
    public ItemEntity(@NotNull @NotBlank String name, @DecimalMin(value = "0.01") BigDecimal price, @NotNull Long amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long itemId;

    @NotNull
    @NotBlank
    private String name;

    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull
    private Long amount;
}
