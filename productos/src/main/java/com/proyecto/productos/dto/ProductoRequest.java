package com.proyecto.productos.dto;

public record ProductoRequest(
		
		String nombre, 
		String descripcion, 
		Double precio, 
		Integer stock
		){

}
