package com.mc.warehouse.WarehouseService.controller;

import com.mc.warehouse.WarehouseService.domain.ItemEntity;
import com.mc.warehouse.WarehouseService.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/greeting")
    public void greeting() {
        Iterable<ItemEntity> all = itemRepository.findAll();

        all.forEach(itemEntity -> {
            System.out.println(itemEntity.getName());
            System.out.println(itemEntity.getPrice());
        });
    }

    @GetMapping("/greet")
    public void greet() {
        ItemEntity itemEntity = new ItemEntity(new BigDecimal(10.0), "teamat", 10L);
        itemRepository.save(itemEntity);
    }

}
