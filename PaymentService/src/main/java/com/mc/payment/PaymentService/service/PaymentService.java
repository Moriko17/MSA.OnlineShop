package com.mc.payment.PaymentService.service;

import com.mc.payment.PaymentService.dataObjects.OfferDto;
import com.mc.payment.PaymentService.domain.TransactionEntity;

public interface PaymentService {
    TransactionEntity changeStatus(Long id, String status);
    TransactionEntity performPayment(OfferDto offerDto);
}
