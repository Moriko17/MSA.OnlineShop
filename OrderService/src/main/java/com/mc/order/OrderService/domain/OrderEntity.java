package com.mc.order.OrderService.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Orders")
public class OrderEntity {

    public OrderEntity() {};

    public OrderEntity(@NotNull String status, @NotNull BigDecimal totalCost, @NotNull Integer totalAmount, @NotNull String userName, @NotNull List<ItemAdditionEntity> items) {
        this.status = status;
        this.totalCost = totalCost;
        this.totalAmount = totalAmount;
        this.userName = userName;
        this.items = items;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull
    private String status;

    @NotNull
    private BigDecimal totalCost;

    @NotNull
    private Integer totalAmount;

    @NotNull
    private String userName;

    @NotNull
    @ElementCollection(targetClass = ItemAdditionEntity.class)
    private List<ItemAdditionEntity> items;

    public void addToList(ItemAdditionEntity itemAdditionEntity) {
        items.add(itemAdditionEntity);
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public String getUserName() {
        return userName;
    }

    public List<ItemAdditionEntity> getItems() {
        return items;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setItems(List<ItemAdditionEntity> items) {
        this.items = items;
    }
}
