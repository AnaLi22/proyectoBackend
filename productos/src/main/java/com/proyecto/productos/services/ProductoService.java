package com.proyecto.productos.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.productos.dto.ProductoRequest;
import com.proyecto.productos.dto.ProductoResponse;

public interface ProductoService {
	List<ProductoResponse> listar();
	
	Optional<ProductoResponse> obtenerPorId(Long id);
	
	ProductoResponse insertar (ProductoRequest request);
	
	ProductoResponse actualizar (ProductoRequest request, Long id);
	
	ProductoResponse eliminar (Long id);
	
		
}
