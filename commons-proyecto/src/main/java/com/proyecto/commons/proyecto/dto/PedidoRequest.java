package com.proyecto.commons.proyecto.dto;

import java.sql.Date;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PedidoRequest(
		@NotNull(message = "El cliente no puede ser nulo")
		Long idCliente,
		
		@NotNull(message = "No puede ser nulo")
		List<ProductoCantidadRequest> productos
		) {

}
