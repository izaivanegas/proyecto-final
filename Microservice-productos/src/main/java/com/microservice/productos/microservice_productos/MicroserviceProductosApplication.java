package com.microservice.productos.microservice_productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProductosApplication.class, args);
	}

}
