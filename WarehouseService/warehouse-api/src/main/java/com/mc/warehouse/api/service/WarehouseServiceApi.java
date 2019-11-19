package com.mc.warehouse.api.service;

import com.mc.warehouse.api.models.ItemCreationDto;
import com.mc.warehouse.api.models.ItemDto;

import java.util.List;

public interface WarehouseServiceApi {
    List<ItemDto> getItems();
    ItemDto getItemById(Long id);
    ItemDto createItem(ItemCreationDto itemCreationDto);
    ItemDto updateItemAmountById(Long id, Long amount);
}
