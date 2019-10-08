package com.mc.order.OrderService.dataObjects;

import lombok.Getter;

@Getter
public class UserDetailsDto {
    private String userName;
    private CardAuthorizationInfo cardAuthorizationInfo;
}
