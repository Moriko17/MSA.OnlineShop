package com.mc.warehouse.WarehouseService;

import com.mc.warehouse.WarehouseService.rabbitmq.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(RabbitConfiguration.class)
public class WarehouseServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WarehouseServiceApplication.class, args);
	}
}
