package com.mc.warehouse.WarehouseService.controller;

import com.mc.warehouse.WarehouseService.DataObjects.ItemCreationDto;
import com.mc.warehouse.WarehouseService.DataObjects.ItemDto;
import com.mc.warehouse.WarehouseService.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api2/warehouse/items")
public class ItemController {
    private WarehouseService warehouseService;
    @Autowired
    public ItemController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<ItemDto> getAll() {
        return warehouseService.getItems();
    }

    @GetMapping("/{id}")
    public ItemDto getOne(@PathVariable Long id) {
        return warehouseService.getItemById(id);
    }

    @PostMapping
    public ItemDto create(@RequestBody ItemCreationDto itemCreationDto) {
        return warehouseService.createItem(itemCreationDto);
    }

    @PutMapping("/{id}/upd/{amount}")
    public ItemDto updateAmount(@PathVariable Long id, @PathVariable Long amount) {
        return warehouseService.updateItemsAmount(id, amount);
    }
}
