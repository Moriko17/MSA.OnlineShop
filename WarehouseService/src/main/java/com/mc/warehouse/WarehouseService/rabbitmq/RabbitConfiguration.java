package com.mc.warehouse.WarehouseService.rabbitmq;

import com.mc.warehouse.WarehouseService.service.WarehouseService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
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
    Logger logger = LoggerFactory.getLogger(RabbitConfiguration.class);

    @Autowired
    public RabbitConfiguration(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("queue1");
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer1() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("queue1");
        container.setMessageListener(new MessageListener() {

            //тут ловим сообщения из queue1
            public void onMessage(Message message) {
                String[] msg = new String(message.getBody()).split(":");
//                logger.info("" + msg[0]);
//                logger.info("" + msg[1]);
                warehouseService.updateItemAmountById(Long.parseLong(msg[0]), Long.parseLong(msg[1]));
                logger.info("received from queue1 : " + new String(message.getBody()));
            }
        });
        return container;
    }
}
