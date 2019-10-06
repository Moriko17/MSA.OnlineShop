package com.mc.warehouse.WarehouseService.service;

import com.mc.warehouse.WarehouseService.DataObjects.ItemCreationDto;
import com.mc.warehouse.WarehouseService.DataObjects.ItemDto;

import java.util.List;

public interface WarehouseService {
    List<ItemDto> getItems();
    ItemDto getItemById(Long id);
    ItemDto createItem(ItemCreationDto itemCreationDto);
    ItemDto updateItemsAmount(Long id, Long amount);
}
