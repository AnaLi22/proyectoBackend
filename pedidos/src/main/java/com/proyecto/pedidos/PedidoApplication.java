package com.proyecto.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.proyecto.commons.proyecto.controllers","com.proyecto.pedidos"})
@EntityScan({"com.proyecto.commons.proyecto.models.entities"})
public class PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

}
