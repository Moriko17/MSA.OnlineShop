package com.mc.warehouse.WarehouseService.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;

public class RabbitConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("rabbitmq");
    }

    @Bean
    public Queue warehouseQueue() {
        return new Queue("warehouseQueue");
    }
}
