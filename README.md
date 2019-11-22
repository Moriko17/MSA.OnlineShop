# MSA.OnlineShop  
This repository is an example of how to get Microservices going using Spring Boot, Spring Cloud, Spring Web and Netflix frameworks.  
## Table of content  
1. [Application Architecture](#Application-Architecture)  
2. [Microservices Overview](#Microservices-Overview)  
2.1 [Eureka](#Eureka)  
2.2 [Gateway](#Gateway)  
2.3. [OrderService](#OrderService)  
2.4. [PaymentService](#PaymentService)  
2.5. [WarehouseService](#WarehouseService)  
3. [Building](#Building)  
3.1 [Via IDE](#Via-Ide)  
3.2 [Via Docker](#Via-Docker)
## Application Architecture  
The application contains:  
* Eureka discovery service.  
* Zuul gateway service.  
* Warehouse service.  
* Order service.  
* Payment service.  
* Postgresql.  
* Rabbitmq.
## Microservices Overview  
### Eureka  
Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers.
### Gateway  
Zuul is an edge service that provides dynamic routing, monitoring, resiliency, security, and more. 
### OrderService  
Order service is used to work with orders, includes: adding items to order, creating new orders and change order's status.
### PaymentService  
Payment server is created for working with transactions. Btw it's can verifi card auth status and perform payment.
### WarehouseService  
Builded for working with items. Includes: creating new item, updating existens item and so on.
## Building  
#### Via IDE.  
Build presented projects, launch rabbitmq and postgres servers localy or in docker container. Create databases: MCA.warehouse, MCA.order, MCA.payment.  
Then try to access gateway on:  
      
      http://localhost:8762  
Gateway endpoints presented in Gateway's readme.  
#### Via Docker.  
Build bootJar via gradle. Then run  
     
     docker-compose up  
Before launching services create databases: MCA.warehouse, MCA.order, MCA.payment.  
      
      docker exec -it msaonlineshop_postgres_1 psql  
      CREATE DATABASE "db name";  
      exit  
Then run  
      
      docker-compose -f docker-compose-services.yml up  
Try to access gateway on:  
      
      http://localhost:8762  
Gateway endpoints presented in Gateway's readme.
