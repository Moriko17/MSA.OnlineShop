package com.mc.warehouse.WarehouseService.service;

import com.mc.warehouse.WarehouseService.DataObjects.ItemCreationDto;
import com.mc.warehouse.WarehouseService.DataObjects.ItemDto;
import com.mc.warehouse.WarehouseService.domain.ItemEntity;
import com.mc.warehouse.WarehouseService.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private ItemRepository itemRepository;
    @Autowired
    public WarehouseServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDto> getAll() {
        List<ItemDto> items = new ArrayList<>();
        itemRepository.findAll().forEach(itemEntity -> {
            items.add(convertToDto(itemEntity));
        });

        return items;
    }

    @Override
    public ItemDto getOne(Long id) {
        ItemEntity itemEntity = itemRepository.findById(id).get();
        return convertToDto(itemEntity);
    }

    @Override
    public ItemDto create(ItemCreationDto itemCreationDto) {
        ItemEntity itemEntity = itemRepository.save(new ItemEntity(
                itemCreationDto.getName(),
                itemCreationDto.getPrice(),
                itemCreationDto.getAmount()
        ));
        return convertToDto(itemEntity);
    }

    @Override
    public ItemDto updateAmount(Long id, Long amount) {
        ItemEntity itemEntity = itemRepository.findById(id).get();
        itemEntity.setAmount(itemEntity.getAmount() + amount);
        return convertToDto(itemRepository.save(itemEntity));
    }

    private ItemDto convertToDto(ItemEntity itemEntity) {
        return new ItemDto(
                itemEntity.getItemId(),
                itemEntity.getName(),
                itemEntity.getPrice(),
                itemEntity.getAmount()
        );
    }
}
