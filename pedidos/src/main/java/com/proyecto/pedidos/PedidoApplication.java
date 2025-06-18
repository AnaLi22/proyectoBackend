package com.proyecto.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.proyecto.commons.proyecto.controllers","com.proyecto.pedidos"})
@EntityScan({"com.proyecto.commons.proyecto.models.entities"})
@EnableFeignClients
public class PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

}
