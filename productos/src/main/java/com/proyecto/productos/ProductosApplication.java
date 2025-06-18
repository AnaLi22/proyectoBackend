package com.proyecto.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.proyecto.commons.proyecto.controllers","com.proyecto.productos"})
@EntityScan({"com.proyecto.commons.proyecto.models.entities"})

public class ProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductosApplication.class, args);
	}

}
