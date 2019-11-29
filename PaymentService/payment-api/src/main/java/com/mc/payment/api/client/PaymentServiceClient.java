package com.mc.payment.api.client;

import com.mc.order.api.models.OrderDto;
import com.mc.payment.api.models.Check;
import com.mc.payment.api.models.TransactionDto;
import com.mc.payment.api.models.UserDetailsDto;
import com.mc.payment.api.service.PaymentServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "payment")
public interface PaymentServiceClient extends PaymentServiceApi {
    @GetMapping
    List<TransactionDto> getTransactions();

    @GetMapping("/{id}")
    TransactionDto getTransactionById(@PathVariable(value = "id") Long id);

    @PutMapping("/{orderId}/payment")
    OrderDto performPayment(@PathVariable(value = "orderId") Long orderId,
                            @RequestBody UserDetailsDto userDetailsDto);

    @GetMapping("/check/{id}")
    Check getCheckByTransactionId(@PathVariable(value = "id") Long id);
}
