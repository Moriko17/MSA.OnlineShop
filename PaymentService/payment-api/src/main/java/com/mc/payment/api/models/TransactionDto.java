package com.mc.payment.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private TransactionStatus status;
    private String userName;
    private Long order_id;
}
