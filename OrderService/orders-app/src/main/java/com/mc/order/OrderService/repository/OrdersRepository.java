package com.mc.order.OrderService.repository;

import com.mc.order.OrderService.domain.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<OrderEntity, Long> {}
