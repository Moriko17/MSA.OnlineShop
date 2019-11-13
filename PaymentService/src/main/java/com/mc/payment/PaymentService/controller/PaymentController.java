package com.mc.payment.PaymentService.controller;

import com.mc.payment.PaymentService.dataObjects.OrderDto;
import com.mc.payment.PaymentService.dataObjects.TransactionDto;
import com.mc.payment.PaymentService.dataObjects.UserDetailsDto;
import com.mc.payment.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

//    @GetMapping
//    public List<TransactionDto> getTransactions() {
//        return paymentService.getTransactions();
//    }
//
//    @GetMapping("/{id}")
//    public TransactionDto getTransactionById(@PathVariable Long id) {
//        return paymentService.getTransactionById(id);
//    }

    @PutMapping("/{orderId}/payment")
    public OrderDto performPayment(@PathVariable Long orderId, @RequestBody UserDetailsDto userDetailsDto) {
        return paymentService.performPayment(orderId, userDetailsDto);
    }
}
