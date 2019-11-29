package com.mc.warehouse.WarehouseService.rabbitmq;

import com.mc.warehouse.WarehouseService.service.WarehouseService;
import com.mc.warehouse.api.models.WarehouseDelta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseListener {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseDelta.class);
    private WarehouseService warehouseService;

    @Autowired public WarehouseListener(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @RabbitListener(queues = "warehouseQueue")
    public void consumeMessage(Message message) {
        WarehouseDelta warehouseDelta = (WarehouseDelta) SerializationUtils.deserialize(message.getBody());
        logger.info("Received from warehouseQueue: id: {}, delta: {}",
                warehouseDelta.getId(), warehouseDelta.getDelta());
        warehouseService.updateItemAmountById(warehouseDelta.getId(), warehouseDelta.getDelta());
    }
}
