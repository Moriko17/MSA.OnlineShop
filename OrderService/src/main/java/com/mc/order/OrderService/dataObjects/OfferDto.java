package com.mc.order.OrderService.dataObjects;

public class OfferDto {
    public OfferDto(String userName, Long orderId) {
        this.userName = userName;
        this.orderId = orderId;
    }

    private String userName;
    private Long orderId;

    public String getUserName() {
        return userName;
    }

    public Long getOrderId() {
        return orderId;
    }
}
