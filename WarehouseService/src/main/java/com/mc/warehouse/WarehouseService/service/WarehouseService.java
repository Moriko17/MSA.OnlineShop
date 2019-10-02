package com.mc.warehouse.WarehouseService.service;

import com.mc.warehouse.WarehouseService.DataObjects.ItemCreationDto;
import com.mc.warehouse.WarehouseService.DataObjects.ItemDto;

import java.util.List;

public interface WarehouseService {
    List<ItemDto> getAll();
    ItemDto getOne(Long id);
    ItemDto create(ItemCreationDto itemCreationDto);
    ItemDto updateAmount(Long id, Long amount);
}
