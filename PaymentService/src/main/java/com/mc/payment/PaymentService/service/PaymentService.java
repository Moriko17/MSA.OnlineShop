package com.mc.payment.PaymentService.service;

import com.mc.payment.PaymentService.dataObjects.TransactionDto;
import com.mc.payment.PaymentService.dataObjects.UserDetailsDto;

import java.util.List;

public interface PaymentService {
    List<TransactionDto> getTransactions();
    TransactionDto getTransactionById(Long id);
    TransactionDto performPayment(Long id, UserDetailsDto userDetailsDto);
}
