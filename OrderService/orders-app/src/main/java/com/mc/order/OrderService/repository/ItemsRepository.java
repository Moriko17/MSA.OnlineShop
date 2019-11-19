package com.mc.order.OrderService.repository;

import com.mc.order.OrderService.domain.ItemAdditionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<ItemAdditionEntity, Long> {}
