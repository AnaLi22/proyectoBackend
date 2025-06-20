package com.proyecto.commons.proyecto.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoRequest(
		@NotNull(message = "El cliente no puede ser nulo")
		Long idCliente,
		
		@NotNull(message = "La lista de productos no puede ser nulo")
		@Size(min = 1, message = "La lista de productos debe contener al menos un producto")
		List<ProductoCantidadRequest> productos,
		
		Long idEstado
		) {

}
