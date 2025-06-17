package com.proyecto.commons.proyecto.dto;

import jakarta.validation.constraints.NotNull;

public record ProductoCantidadRequest(
		
		@NotNull(message = "El ID de Producto no puede ser nulo")
		Long idProducto,
		
		@NotNull(message = "La cantidad no puede ser nula")
		Integer cantidad
) {}
