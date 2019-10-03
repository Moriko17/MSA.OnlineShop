package com.mc.order.OrderService.dataObjects;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    public OrderDto(Long id, String status, BigDecimal totalCost, Integer totalAmount, String userName, List<ItemDto> items) {
        this.id = id;
        this.status = status;
        this.totalCost = totalCost;
        this.totalAmount = totalAmount;
        this.userName = userName;
        this.items = items;
    }

    private Long id;
    private String status;
    private BigDecimal totalCost;
    private Integer totalAmount;
    private String userName;
    private List<ItemDto> items;

    public Long getId() {
        return id;
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

    public List<ItemDto> getItems() {
        return items;
    }
}
