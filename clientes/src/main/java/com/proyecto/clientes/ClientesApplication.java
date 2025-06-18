package com.proyecto.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.proyecto.commons.proyecto.controllers","com.proyecto.clientes"})
@EntityScan({"com.proyecto.commons.proyecto.models.entities"})

public class ClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

}
