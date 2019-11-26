package com.mc.payment.PaymentService.service;

import com.mc.order.api.client.OrderServiceClient;
import com.mc.order.api.models.OrderDto;
import com.mc.payment.PaymentService.domain.TransactionEntity;
import com.mc.payment.PaymentService.repository.TransactionRepository;
import com.mc.payment.api.models.*;
import com.mc.warehouse.api.client.WarehouseServiceClient;
import com.mc.warehouse.api.models.ItemDto;
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
    private OrderServiceClient orderServiceClient;
    private WarehouseServiceClient warehouseServiceClient;

    @Autowired
    public PaymentServiceImpl(TransactionRepository transactionRepository,
                              OrderServiceClient orderServiceClient, WarehouseServiceClient warehouseServiceClient) {
        this.transactionRepository = transactionRepository;
        this.orderServiceClient = orderServiceClient;
        this.warehouseServiceClient = warehouseServiceClient;
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
        com.mc.order.api.models.OrderStatus orderStatus;
        switch (userDetailsDto.getCardAuthorizationInfo()) {
            case UNAUTHORIZED:
                transactionStatus = TransactionStatus.FAILED;
                logger.info("Payment for order with id {} was rejected", id);
                orderStatus = com.mc.order.api.models.OrderStatus.FAILED;
                break;
            case AUTHORIZED:
                transactionStatus = TransactionStatus.PAID;
                logger.info("Payment for order with id {} was performed", id);
                orderStatus = com.mc.order.api.models.OrderStatus.PAID;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userDetailsDto.getCardAuthorizationInfo());
        }

        OrderDto orderDto = orderServiceClient.changeOrderStatus(id, orderStatus);

        TransactionEntity transactionEntity = transactionRepository.save(new TransactionEntity(
                transactionStatus,
                userDetailsDto.getUserName(),
                id
        ));
        logger.info("Created new transaction with id {}", transactionEntity.getId());

        return orderDto;
    }

    @Override
    public Check getCheckByTransactionId(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElseThrow(RuntimeException::new);
        OrderDto orderDto = orderServiceClient.getOrderById(transactionEntity.getOrder_id());
        List<ItemInOrder> itemsInOrder = new ArrayList<>();
        orderDto.getItems().forEach(item -> {
            ItemDto itemDto = warehouseServiceClient.getItemById(item.getItemId());
            ItemInOrder itemInOrder = new ItemInOrder(itemDto.getName(), itemDto.getPrice());
            itemsInOrder.add(itemInOrder);
        });

        return new Check(id, transactionEntity.getOrder_id(), itemsInOrder);
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
