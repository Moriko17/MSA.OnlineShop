package com.mc.payment.PaymentService.service;

import com.mc.payment.PaymentService.dataObjects.TransactionDto;
import com.mc.payment.PaymentService.dataObjects.UserDetailsDto;
import com.mc.payment.PaymentService.domain.TransactionEntity;
import com.mc.payment.PaymentService.domain.TransactionStatus;
import com.mc.payment.PaymentService.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TransactionDto performPayment(Long id, UserDetailsDto userDetailsDto) {
        TransactionStatus transactionStatus;
        switch (userDetailsDto.getCardAuthorizationInfo()) {
            case UNAUTHORIZED:
                transactionStatus = TransactionStatus.FAILED;
                logger.info("Payment for order with id {} was rejected", id);
                break;
            case AUTHORIZED:
                transactionStatus = TransactionStatus.PAID;
                logger.info("Payment for order with id {} was performed", id);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userDetailsDto.getCardAuthorizationInfo());
        }

        //todo change order's status
        TransactionEntity transactionEntity = new TransactionEntity(
                transactionStatus,
                userDetailsDto.getUserName(),
                id
        );
        logger.info("Created new transaction with id {}", transactionEntity.getId());

        return convertTransactionEntityToTransactionDto(transactionRepository.save(transactionEntity));
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
