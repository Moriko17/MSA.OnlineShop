package com.mc.warehouse.WarehouseService.service;

import com.mc.warehouse.WarehouseService.DataObjects.ItemCreationDto;
import com.mc.warehouse.WarehouseService.DataObjects.ItemDto;
import com.mc.warehouse.WarehouseService.domain.ItemEntity;
import com.mc.warehouse.WarehouseService.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private ItemRepository itemRepository;
    @Autowired
    public WarehouseServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDto> getItems() {
        List<ItemDto> items = new ArrayList<>();
        itemRepository.findAll().forEach(itemEntity -> items.add(convertItemEntityToItemDto(itemEntity)));
        logger.info("Returned collection with {} items", items.size());

        return items;
    }

    @Override
    public ItemDto getItemById(Long id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(RuntimeException::new);
        logger.info("Returned item with id {}", itemEntity.getItemId());

        return convertItemEntityToItemDto(itemEntity);
    }

    @Override
    public ItemDto createItem(ItemCreationDto itemCreationDto) {
        ItemEntity itemEntity = itemRepository.save(new ItemEntity(
                itemCreationDto.getName(),
                itemCreationDto.getPrice(),
                itemCreationDto.getAmount()
        ));
        logger.info("Item with id {} was created", itemEntity.getItemId());

        return convertItemEntityToItemDto(itemEntity);
    }

    @Override
    public ItemDto updateItemAmountById(Long id, Long amount) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(RuntimeException::new);
        itemEntity.setAmount(itemEntity.getAmount() + amount);
        logger.info("Amount of item with id {} was changed to {}", itemEntity.getItemId(), itemEntity.getAmount());

        return convertItemEntityToItemDto(itemRepository.save(itemEntity));
    }

    private ItemDto convertItemEntityToItemDto(ItemEntity itemEntity) {
        return new ItemDto(
                itemEntity.getItemId(),
                itemEntity.getName(),
                itemEntity.getPrice(),
                itemEntity.getAmount()
        );
    }
}
