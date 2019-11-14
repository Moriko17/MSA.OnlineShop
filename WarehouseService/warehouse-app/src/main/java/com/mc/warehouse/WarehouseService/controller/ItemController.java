package com.mc.warehouse.WarehouseService.controller;

import com.mc.warehouse.WarehouseService.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mc.warehouse.api.models.ItemDto;
import com.mc.warehouse.api.models.ItemCreationDto;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private WarehouseService warehouseService;
    @Autowired
    public ItemController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<ItemDto> getItems() {
        return warehouseService.getItems();
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        return warehouseService.getItemById(id);
    }

    @PostMapping
    public ItemDto createItem(@RequestBody ItemCreationDto itemCreationDto) {
        return warehouseService.createItem(itemCreationDto);
    }

    @PutMapping("/{id}/addition/{delta}")
    public ItemDto updateItemAmountById(@PathVariable Long id, @PathVariable Long delta) {
        return warehouseService.updateItemAmountById(id, delta);
    }
}
