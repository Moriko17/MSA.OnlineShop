package com.mc.payment.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Check {
    private Long transactionId;
    private Long orderId;
    private List<ItemInOrder> itemsInOrder;
}
