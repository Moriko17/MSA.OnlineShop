package com.mc.warehouse.WarehouseService.service;

import com.mc.warehouse.api.Models.ItemDto;
import com.mc.warehouse.api.Models.ItemCreationDto;

import java.util.List;

public interface WarehouseService {
    List<ItemDto> getItems();
    ItemDto getItemById(Long id);
    ItemDto createItem(ItemCreationDto itemCreationDto);
    ItemDto updateItemAmountById(Long id, Long amount);
}
