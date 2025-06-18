package com.proyecto.commons.proyecto.dto;

import java.util.Date;
import java.util.List;

public record PedidoResponse(

		Long id,
		ClienteResponse cliente,
		Double total,
		Date fechaCreacion,
		Long idEstado,
		List<ProductoResponse> productos
		) {
	
}
