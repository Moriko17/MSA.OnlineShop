package com.mc.order.OrderService.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ItemAddition")
public class ItemAdditionEntity {
    public ItemAdditionEntity(@NotNull Long itemId, @NotNull Integer amount, @NotNull @NotBlank String userName) {
        this.itemId = itemId;
        this.amount = amount;
        this.userName = userName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long itemId;

    @NotNull
    private Integer amount;

    @NotNull
    @NotBlank
    private String userName;
}
