package com.mc.order.OrderService.dataObjects;

public class ItemDto {

    public ItemDto(Long itemId, Integer amount, String userName) {
        this.itemId = itemId;
        this.amount = amount;
        this.userName = userName;
    }

    private Long itemId;
    private Integer amount;
    private String userName;

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
