package com.mc.warehouse.api.client;

import com.mc.warehouse.api.models.ItemCreationDto;
import com.mc.warehouse.api.models.ItemDto;
import com.mc.warehouse.api.service.WarehouseServiceApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "warehouse/items")
public interface WarehouseServiceClient extends WarehouseServiceApi {
    @GetMapping
    List<ItemDto> getItems();

    @GetMapping("/{id}")
    ItemDto getItemById(@PathVariable(value = "id") Long id);

    @PostMapping
    ItemDto createItem(@RequestBody ItemCreationDto itemCreationDto);

    @PutMapping("/{id}/addition/{delta}")
    ItemDto updateItemAmountById(@PathVariable(value = "id") Long id, @PathVariable(value = "delta") Long delta);
}
