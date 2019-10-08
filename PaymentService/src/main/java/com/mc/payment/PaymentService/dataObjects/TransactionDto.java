package com.mc.payment.PaymentService.dataObjects;

import com.mc.payment.PaymentService.domain.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TransactionDto {
    private Long id;
    private TransactionStatus status;
    private String userName;
    private Long order_id;
}
