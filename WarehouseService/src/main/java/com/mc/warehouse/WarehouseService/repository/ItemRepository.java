package com.mc.warehouse.WarehouseService.repository;

import com.mc.warehouse.WarehouseService.domain.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, Long> {}
