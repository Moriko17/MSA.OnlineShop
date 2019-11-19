package com.mc.payment.PaymentService.service;

import com.mc.order.api.models.OrderDto;
import com.mc.payment.api.models.TransactionDto;
import com.mc.payment.api.models.UserDetailsDto;

import java.util.List;

public interface PaymentService {
    List<TransactionDto> getTransactions();
    TransactionDto getTransactionById(Long id);
    OrderDto performPayment(Long id, UserDetailsDto userDetailsDto);
}
