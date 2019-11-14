package com.mc.warehouse.WarehouseService.rabbitmq;

import com.mc.warehouse.WarehouseService.service.WarehouseService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RabbitConfiguration {
    private WarehouseService warehouseService;
    private Logger logger = LoggerFactory.getLogger(RabbitConfiguration.class);

    @Autowired
    public RabbitConfiguration(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public Queue warehouseQueue() {
        return new Queue("warehouseQueue");
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("warehouseQueue");
        container.setMessageListener(message -> {
            logger.info("Received from warehouseQueue : " + new String(message.getBody()));
            String[] msg = new String(message.getBody()).split(":");
            warehouseService.updateItemAmountById(Long.parseLong(msg[0]), Long.parseLong(msg[1]));
        });
        return container;
    }
}
