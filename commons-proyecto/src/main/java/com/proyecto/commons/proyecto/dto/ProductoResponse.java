package com.proyecto.commons.proyecto.dto;

public record ProductoResponse(
		
		Long id, 
		String nombre, 
		String descripcion,
		Double precio,
		Integer stock 
		){
	
}
