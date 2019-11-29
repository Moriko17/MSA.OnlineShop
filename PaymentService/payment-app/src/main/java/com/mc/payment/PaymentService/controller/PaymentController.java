package com.mc.payment.PaymentService.controller;

import com.mc.order.api.models.OrderDto;
import com.mc.payment.PaymentService.service.PaymentService;
import com.mc.payment.api.models.Check;
import com.mc.payment.api.models.TransactionDto;
import com.mc.payment.api.models.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    private PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<TransactionDto> getTransactions() {
        return paymentService.getTransactions();
    }

    @GetMapping("/{id}")
    public TransactionDto getTransactionById(@PathVariable(value = "id") Long id) {
        return paymentService.getTransactionById(id);
    }

    @PutMapping("/{orderId}/payment")
    public OrderDto performPayment(@PathVariable(value = "orderId") Long orderId,
                                   @RequestBody UserDetailsDto userDetailsDto) {
        return paymentService.performPayment(orderId, userDetailsDto);
    }

    @GetMapping("/check/{id}")
    public Check getCheckByTransactionId(@PathVariable(value = "id") Long id) {
        return paymentService.getCheckByTransactionId(id);
    }
}
