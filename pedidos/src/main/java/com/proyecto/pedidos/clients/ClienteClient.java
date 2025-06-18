package com.proyecto.pedidos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyecto.commons.proyecto.dto.ClienteResponse;

@FeignClient(name = "clientes")
public interface ClienteClient {
	
	@GetMapping("/{id}")
	ClienteResponse getCliente(@PathVariable Long id);

}
