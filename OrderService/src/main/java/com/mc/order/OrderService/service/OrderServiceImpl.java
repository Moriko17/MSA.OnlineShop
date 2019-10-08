package com.mc.order.OrderService.service;

import com.mc.order.OrderService.dataObjects.ItemDto;
import com.mc.order.OrderService.dataObjects.OrderDto;
import com.mc.order.OrderService.domain.ItemAdditionEntity;
import com.mc.order.OrderService.domain.OrderEntity;
import com.mc.order.OrderService.domain.OrdersStatus;
import com.mc.order.OrderService.repository.ItemsRepository;
import com.mc.order.OrderService.repository.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<OrderDto> getOrders() {
        List<OrderDto> orders = new ArrayList<>();
        ordersRepository.findAll().forEach(orderEntity -> orders.add(convertOrderEntityToOrderDto(orderEntity)));
        logger.info("Returns collection with {} orders", orders.size());

        return orders;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        OrderEntity orderEntity = ordersRepository.findById(id).orElseThrow(RuntimeException::new);
        logger.info("Returns order with id {}", id);

        return convertOrderEntityToOrderDto(orderEntity);
    }

    @Override
    public OrderDto addItemToOrder(String id, ItemDto itemDto) {
        OrderEntity orderEntity;
        if (id.toLowerCase().equals("null")) {
            orderEntity = createOrder(itemDto.getUserName());
            logger.info("New order with id {} was created", orderEntity.getOrderId());
        } else {
            Long parsedId = Long.parseLong(id);
            orderEntity = ordersRepository.findById(parsedId).orElseThrow(RuntimeException::new);
        }

        //todo check does warehouse have enough items to add

        ItemAdditionEntity itemAdditionEntity = convertItemDtoToItemAdditionEntity(itemDto);
        itemsRepository.save(itemAdditionEntity);

        orderEntity.addToList(itemAdditionEntity);
        orderEntity.setTotalAmount(orderEntity.getTotalAmount() + itemDto.getAmount());
        //todo updating total cost
        //todo updating warehouse's amount

        logger.info("Item with id {} was added to order with id {}",
                itemAdditionEntity.getItemId(),
                orderEntity.getOrderId());

        return convertOrderEntityToOrderDto(ordersRepository.save(orderEntity));
    }

    @Override
    public OrderDto changeOrdersStatus(Long id, OrdersStatus status) {
        OrderEntity orderEntity = ordersRepository.findById(id).orElseThrow(RuntimeException::new);
        orderEntity.setStatus(status);
        logger.info("Order status was changed to {}", status);
        //todo updating warehouse's amount in case of rejection

        return convertOrderEntityToOrderDto(ordersRepository.save(orderEntity));
    }

    private OrderEntity createOrder(String userName) {
        return new OrderEntity(OrdersStatus.COLLECTING,
                new BigDecimal(0),
                0,
                userName,
                new ArrayList<>());
    }

    private OrderDto convertOrderEntityToOrderDto(OrderEntity orderEntity) {
        return new OrderDto(
                orderEntity.getOrderId(),
                orderEntity.getStatus(),
                orderEntity.getTotalCost(),
                orderEntity.getTotalAmount(),
                orderEntity.getUserName(),
                convertItemAdditionEntityToItemsDto(orderEntity.getItems())
        );
    }

    private List<ItemDto> convertItemAdditionEntityToItemsDto(List<ItemAdditionEntity> itemAdditionEntities) {
        List<ItemDto> items = new ArrayList<>();
        itemAdditionEntities.forEach(itemAdditionEntity -> items.add(new ItemDto(
                itemAdditionEntity.getItemId(),
                itemAdditionEntity.getAmount(),
                itemAdditionEntity.getUserName()
        )));
        return items;
    }

    private ItemAdditionEntity convertItemDtoToItemAdditionEntity(ItemDto itemDto) {
        return new ItemAdditionEntity(
                itemDto.getItemId(),
                itemDto.getAmount(),
                itemDto.getUserName());
    }
}
