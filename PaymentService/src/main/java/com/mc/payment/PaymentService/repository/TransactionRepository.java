package com.mc.payment.PaymentService.repository;

import com.mc.payment.PaymentService.domain.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

}
