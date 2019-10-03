package com.mc.order.OrderService.service;

import com.mc.order.OrderService.dataObjects.ItemDto;
import com.mc.order.OrderService.dataObjects.OfferDto;
import com.mc.order.OrderService.dataObjects.OrderDto;
import com.mc.order.OrderService.dataObjects.UserDetailsDto;
import com.mc.order.OrderService.domain.ItemAdditionEntity;
import com.mc.order.OrderService.domain.OrderEntity;
import com.mc.order.OrderService.repository.ItemsRepository;
import com.mc.order.OrderService.repository.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrdersRepository ordersRepository;
    private ItemsRepository itemsRepository;
    @Autowired
    public OrderServiceImpl(OrdersRepository ordersRepository, ItemsRepository itemsRepository) {
        this.ordersRepository = ordersRepository;
        this.itemsRepository = itemsRepository;
    }

    @Override
    public List<OrderDto> getAll() {
        List<OrderDto> orders = new ArrayList<>();
        ordersRepository.findAll().forEach(orderEntity -> {
            orders.add(convertToOrderDto(orderEntity));
        });
        logger.info("Returns orders collection");

        return orders;
    }

    @Override
    public OrderDto getOne(Long id) {
        OrderEntity orderEntity = ordersRepository.findById(id).get();
        logger.info("Returns order with id {}", id);

        return convertToOrderDto(orderEntity);
    }

    @Override
    public OrderDto addToOrder(@Nullable Long id, ItemDto itemDto) {
        OrderEntity orderEntity;
        if (id == null) {
            orderEntity = new OrderEntity(
                    "Collecting",
                    new BigDecimal(0),
                    0,
                    itemDto.getUserName(),
                    new ArrayList<ItemAdditionEntity>());
            logger.info("New order with id {} was created", orderEntity.getOrderId());
        } else {
            orderEntity = ordersRepository.findById(id).get();
        }

        ItemAdditionEntity itemAdditionEntity = convertToItemAdditionEntity(itemDto);
        itemsRepository.save(itemAdditionEntity);

        orderEntity.addToList(itemAdditionEntity);

        orderEntity.setTotalAmount(orderEntity.getTotalAmount() + itemDto.getAmount());
        //todo need to chat with Item service to get item's price by itemId
        //orderEntity.setTotalCost(orderEntity.getTotalCost() + );

        logger.info("New item was added to order with id {}", orderEntity.getOrderId());

        return convertToOrderDto(ordersRepository.save(orderEntity));
    }

    @Override
    public OrderDto changeStatus(Long id, String status) {
        OrderEntity orderEntity = ordersRepository.findById(id).get();
        orderEntity.setStatus(status);
        logger.info("Order status was changed to {}", status);

        return convertToOrderDto(ordersRepository.save(orderEntity));
    }

    @Override
    public OfferDto preparePayment(Long id, UserDetailsDto userDetailsDto) {
        //TODO return something else if card not auth.
        logger.info("Offer obj for order with id {} was created", id);
        return new OfferDto(userDetailsDto.getUserName(), id);
    }

    private OrderDto convertToOrderDto(OrderEntity orderEntity) {
        return new OrderDto(
                orderEntity.getOrderId(),
                orderEntity.getStatus(),
                orderEntity.getTotalCost(),
                orderEntity.getTotalAmount(),
                orderEntity.getUserName(),
                convertToItemsDto(orderEntity.getItems())
        );
    }

    private List<ItemDto> convertToItemsDto(List<ItemAdditionEntity> itemAdditionEntities) {
        List<ItemDto> items = new ArrayList<>();
        itemAdditionEntities.forEach(itemAdditionEntity -> {
            items.add(new ItemDto(
                    itemAdditionEntity.getItemId(),
                    itemAdditionEntity.getAmount(),
                    itemAdditionEntity.getUserName()
            ));
        });
        return items;
    }

    private ItemAdditionEntity convertToItemAdditionEntity(ItemDto itemDto) {
        return new ItemAdditionEntity(
                itemDto.getItemId(),
                itemDto.getAmount(),
                itemDto.getUserName());
    }
}
