package com.proyecto.commons.proyecto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductoCantidadRequest(
		
		@NotNull(message = "El ID de Producto no puede ser nulo")
		Long idProducto,
		
		@NotNull(message = "La cantidad no puede ser nula")
		@Min(value = 1 , message = "La cantidad debe ser minimo 1")
		Integer cantidad
) {}
