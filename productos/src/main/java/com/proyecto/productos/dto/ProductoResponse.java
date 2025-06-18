package com.proyecto.productos.dto;

public record ProductoResponse(
		
		Long id, 
		String nombre, 
		String descripcion,
		Double precio,
		Integer stock 
		){
	
}
