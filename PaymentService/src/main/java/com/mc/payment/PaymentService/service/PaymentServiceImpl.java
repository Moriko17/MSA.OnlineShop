package com.mc.payment.PaymentService.service;

import com.mc.payment.PaymentService.dataObjects.OrderDto;
import com.mc.payment.PaymentService.dataObjects.OrderStatus;
import com.mc.payment.PaymentService.dataObjects.TransactionDto;
import com.mc.payment.PaymentService.dataObjects.UserDetailsDto;
import com.mc.payment.PaymentService.domain.TransactionEntity;
import com.mc.payment.PaymentService.domain.TransactionStatus;
import com.mc.payment.PaymentService.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private TransactionRepository transactionRepository;
    @Autowired
    public PaymentServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDto> getTransactions() {
        List<TransactionDto> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactionEntity ->
                transactions.add(convertTransactionEntityToTransactionDto(transactionEntity)));
        logger.info("Returns collection with {} transactions", transactions.size());

        return transactions;
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElseThrow(RuntimeException::new);
        logger.info("Returns transaction with id {}", id);

        return convertTransactionEntityToTransactionDto(transactionEntity);
    }

    @Override
    public OrderDto performPayment(Long id, UserDetailsDto userDetailsDto) {
        TransactionStatus transactionStatus;
        OrderStatus orderStatus;
        switch (userDetailsDto.getCardAuthorizationInfo()) {
            case UNAUTHORIZED:
                transactionStatus = TransactionStatus.FAILED;
                logger.info("Payment for order with id {} was rejected", id);
                orderStatus = OrderStatus.FAILED;
                break;
            case AUTHORIZED:
                transactionStatus = TransactionStatus.PAID;
                logger.info("Payment for order with id {} was performed", id);
                orderStatus = OrderStatus.PAID;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userDetailsDto.getCardAuthorizationInfo());
        }

        RestTemplate restTemplate = new RestTemplate();
        String orderURL = "http://localhost:8082/";
        restTemplate.put(orderURL + id + "/status/" + orderStatus, OrderDto.class);

        ResponseEntity<OrderDto> response = restTemplate.getForEntity(orderURL + id, OrderDto.class);
        OrderDto orderDto = response.getBody();

        TransactionEntity transactionEntity = transactionRepository.save(new TransactionEntity(
                transactionStatus,
                userDetailsDto.getUserName(),
                id
        ));
        logger.info("Created new transaction with id {}", transactionEntity.getId());

//        return convertTransactionEntityToTransactionDto(transactionRepository.save(transactionEntity));
        return orderDto;
    }

    private TransactionDto convertTransactionEntityToTransactionDto(TransactionEntity transactionEntity) {
        return new TransactionDto(
                transactionEntity.getId(),
                transactionEntity.getStatus(),
                transactionEntity.getUserName(),
                transactionEntity.getOrder_id()
        );
    }
}
