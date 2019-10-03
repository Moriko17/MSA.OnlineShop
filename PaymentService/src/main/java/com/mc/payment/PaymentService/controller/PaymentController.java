package com.mc.payment.PaymentService.controller;


import com.mc.payment.PaymentService.dataObjects.OfferDto;
import com.mc.payment.PaymentService.domain.TransactionEntity;
import com.mc.payment.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2/payment")
public class PaymentController {
    private PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/{transactionId}/status/{status}")
    public TransactionEntity changeStatus(@PathVariable Long transactionId, @PathVariable String status) {
        return paymentService.changeStatus(transactionId, status);
    }

    @PutMapping("/pay")
    public TransactionEntity performPayment(OfferDto offerDto) {
        return paymentService.performPayment(offerDto);
    }
}
