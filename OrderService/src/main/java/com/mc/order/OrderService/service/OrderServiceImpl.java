package com.mc.order.OrderService.service;

import com.mc.order.OrderService.dataObjects.FilledItemDto;
import com.mc.order.OrderService.dataObjects.ItemDto;
import com.mc.order.OrderService.dataObjects.OrderDto;
import com.mc.order.OrderService.domain.ItemAdditionEntity;
import com.mc.order.OrderService.domain.OrderEntity;
import com.mc.order.OrderService.domain.OrderStatus;
import com.mc.order.OrderService.repository.ItemsRepository;
import com.mc.order.OrderService.repository.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrdersRepository ordersRepository;
    private ItemsRepository itemsRepository;
    private AmqpTemplate template;
    @Autowired
    public OrderServiceImpl(OrdersRepository ordersRepository, ItemsRepository itemsRepository, AmqpTemplate template) {
        this.ordersRepository = ordersRepository;
        this.itemsRepository = itemsRepository;
        this.template = template;
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
            orderEntity = ordersRepository.save(createOrder(itemDto.getUserName()));
            logger.info("New order with id {} was created", orderEntity.getOrderId());
        } else {
            Long parsedId = Long.parseLong(id);
            orderEntity = ordersRepository.findById(parsedId).orElseThrow(RuntimeException::new);
        }

        RestTemplate restTemplate = new RestTemplate();
        String warehouseURL = "http://localhost:8081/items/";
        ResponseEntity<FilledItemDto> response
                = restTemplate.getForEntity(warehouseURL + itemDto.getItemId(), FilledItemDto.class);
        FilledItemDto filledItemDto = response.getBody();

        assert filledItemDto != null;
        if(filledItemDto.getAmount() >= itemDto.getAmount()) {
            ItemAdditionEntity itemAdditionEntity = convertItemDtoToItemAdditionEntity(itemDto);
            itemsRepository.save(itemAdditionEntity);

            orderEntity.addToList(itemAdditionEntity);
            orderEntity.setTotalAmount(orderEntity.getTotalAmount() + itemDto.getAmount());
            orderEntity.setTotalCost(orderEntity.getTotalCost()
                    .add(filledItemDto.getPrice().multiply(new BigDecimal(itemDto.getAmount()))));

            logger.info("Sending message to warehouseQueue");
            template.convertAndSend("warehouseQueue",
                    ""+itemDto.getItemId()+":-"+itemDto.getAmount());

            logger.info("Item with id {} was added to order with id {}",
                    itemAdditionEntity.getItemId(),
                    orderEntity.getOrderId());
        } else logger.info("Not enough items: {}", filledItemDto.getName());

        return convertOrderEntityToOrderDto(ordersRepository.save(orderEntity));
    }

    @Override
    public OrderDto changeOrderStatus(Long id, OrderStatus status) {
        OrderEntity orderEntity = ordersRepository.findById(id).orElseThrow(RuntimeException::new);
        orderEntity.setStatus(status);
        logger.info("Order's status with id {} was changed to {}", id, status);

        if(status.equals(OrderStatus.CANCELED) || status.equals(OrderStatus.FAILED)) {
            List<ItemAdditionEntity> items = orderEntity.getItems();
            items.forEach(itemAdditionEntity -> {
                logger.info("Send message with id = {} and delta = {}",
                        itemAdditionEntity.getItemId(), itemAdditionEntity.getAmount());
                template.convertAndSend("warehouseQueue", ""+itemAdditionEntity.getItemId()
                        +":"+itemAdditionEntity.getAmount());
            });
        }

        return convertOrderEntityToOrderDto(ordersRepository.save(orderEntity));
    }

    private OrderEntity createOrder(String userName) {
        return new OrderEntity(OrderStatus.COLLECTING,
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
