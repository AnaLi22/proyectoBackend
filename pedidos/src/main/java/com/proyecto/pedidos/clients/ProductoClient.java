package com.proyecto.pedidos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyecto.commons.proyecto.dto.ProductoResponse;

@FeignClient(name = "productos")
public interface ProductoClient {
	
	@GetMapping("/{id}")
	ProductoResponse getProducto(@PathVariable Long id);

}
