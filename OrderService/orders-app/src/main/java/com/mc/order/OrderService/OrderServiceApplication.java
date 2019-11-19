package com.mc.order.OrderService;

import com.mc.order.OrderService.rabbitmq.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(RabbitConfiguration.class)
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.mc.warehouse.api.client", "com.mc.order.OrderService"})
public class OrderServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
