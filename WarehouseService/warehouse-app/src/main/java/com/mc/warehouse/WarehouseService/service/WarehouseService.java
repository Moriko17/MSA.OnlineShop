package com.mc.warehouse.WarehouseService.service;

import com.mc.warehouse.api.models.ItemDto;
import com.mc.warehouse.api.models.ItemCreationDto;

import java.util.List;

public interface WarehouseService {
    List<ItemDto> getItems();
    ItemDto getItemById(Long id);
    ItemDto createItem(ItemCreationDto itemCreationDto);
    ItemDto updateItemAmountById(Long id, Long amount);
}
