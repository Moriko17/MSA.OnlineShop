package com.mc.order.OrderService.dataObjects;

public class UserDetailsDto {
    private String userName;
    private CardAuthorizationInfo cardAuthorizationInfo;

    public String getUserName() {
        return userName;
    }

    public CardAuthorizationInfo getCardAuthorizationInfo() {
        return cardAuthorizationInfo;
    }
}
