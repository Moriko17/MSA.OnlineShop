package com.mc.payment.PaymentService.dataObjects;

import lombok.Getter;

@Getter
public class UserDetailsDto {
    private String userName;
    private CardAuthorizationInfo cardAuthorizationInfo;
}