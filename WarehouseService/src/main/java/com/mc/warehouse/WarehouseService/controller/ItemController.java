package com.mc.warehouse.WarehouseService.controller;

import com.mc.warehouse.WarehouseService.DataObjects.ItemCreationDto;
import com.mc.warehouse.WarehouseService.DataObjects.ItemDto;
import com.mc.warehouse.WarehouseService.domain.ItemEntity;
import com.mc.warehouse.WarehouseService.repository.ItemRepository;
import com.mc.warehouse.WarehouseService.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
        return warehouseService.getAll();
    }

    @GetMapping("/{id}")
    public ItemDto getOne(@PathVariable Long id) {
        return warehouseService.getOne(id);
    }

    @PostMapping
    public ItemDto create(@RequestBody ItemCreationDto itemCreationDto) {
        return warehouseService.create(itemCreationDto);
    }

    @PutMapping("/{id}/upd/{amount}")
    public ItemDto updateAmount(@PathVariable Long id, @PathVariable Long amount) {
        return warehouseService.updateAmount(id, amount);
    }

    @GetMapping("/test")
    public ItemDto create() {
        ItemCreationDto itemCreationDto = new ItemCreationDto("Tiamat", new BigDecimal(2000), 9L);
        return warehouseService.create(itemCreationDto);
    }

}
