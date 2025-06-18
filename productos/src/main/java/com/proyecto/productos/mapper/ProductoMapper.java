package com.proyecto.productos.mapper;

import org.springframework.stereotype.Component;

import com.proyecto.commons.proyecto.dto.ProductoRequest;
import com.proyecto.commons.proyecto.dto.ProductoResponse;
import com.proyecto.commons.proyecto.mappers.CommonMapper;
import com.proyecto.commons.proyecto.models.entities.Producto;

@Component
public class ProductoMapper extends CommonMapper<ProductoRequest, ProductoResponse, Producto>{
	
	@Override
	public ProductoResponse entityToDTO(Producto entity) {
		if (entity != null) {
			ProductoResponse response = new ProductoResponse(
					entity.getId(),
					entity.getNombre(),
					entity.getDescripcion(),
					entity.getPrecio(),
					entity.getStock());
			return response;
		}
		return null;
	
	}

	@Override
	public Producto dtoToEntity(ProductoRequest request) {
		if (request != null) {
			Producto producto = new Producto();
			producto.setNombre(request.nombre());
			producto.setDescripcion(request.descripcion());
			producto.setPrecio(request.precio());
			producto.setStock(request.stock());
			return producto;		
		}
		return null;
	}

}