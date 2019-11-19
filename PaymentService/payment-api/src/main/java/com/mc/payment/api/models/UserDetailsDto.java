package com.mc.payment.api.models;

import lombok.Getter;

@Getter
public class UserDetailsDto {
    private String userName;
    private CardAuthorizationInfo cardAuthorizationInfo;
}