package com.mc.payment.PaymentService.service;

import com.mc.payment.PaymentService.dataObjects.OfferDto;
import com.mc.payment.PaymentService.domain.TransactionEntity;
import com.mc.payment.PaymentService.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private TransactionRepository transactionRepository;
    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    public PaymentServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionEntity changeStatus(Long id, String status) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).get();
        transactionEntity.setStatus(status);
        logger.info("Changed status to {} for transaction with id {}", status, id);

        return transactionRepository.save(transactionEntity);
    }

    @Override
    public TransactionEntity performPayment(OfferDto offerDto) {
        //todo updating warehouse
        TransactionEntity transactionEntity = new TransactionEntity(
                "In-progress",
                offerDto.getUserName(),
                offerDto.getOrderId()
        );
        logger.info("Created new transaction with id {}", transactionEntity.getId());

        return transactionRepository.save(transactionEntity);
    }
}
