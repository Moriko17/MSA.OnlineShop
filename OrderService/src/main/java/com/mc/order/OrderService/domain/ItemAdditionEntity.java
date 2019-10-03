package com.mc.order.OrderService.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ItemAddition")
public class ItemAdditionEntity {

    public ItemAdditionEntity() {}

    public ItemAdditionEntity(@NotNull Long itemId, @NotNull Integer amount, @NotNull String userName) {
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
    private String userName;

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getUserName() {
        return userName;
    }
}
