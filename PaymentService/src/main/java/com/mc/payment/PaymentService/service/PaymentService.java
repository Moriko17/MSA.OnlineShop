package com.mc.payment.PaymentService.service;

import com.mc.payment.PaymentService.dataObjects.OrderDto;
import com.mc.payment.PaymentService.dataObjects.TransactionDto;
import com.mc.payment.PaymentService.dataObjects.UserDetailsDto;

import java.util.List;

public interface PaymentService {
    List<TransactionDto> getTransactions();
    TransactionDto getTransactionById(Long id);
    OrderDto performPayment(Long id, UserDetailsDto userDetailsDto);
}
