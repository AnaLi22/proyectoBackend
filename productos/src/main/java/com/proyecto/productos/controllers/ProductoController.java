package com.proyecto.productos.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.proyecto.commons.proyecto.controllers.CommonController;
import com.proyecto.commons.proyecto.dto.ProductoRequest;
import com.proyecto.commons.proyecto.dto.ProductoResponse;
import com.proyecto.productos.services.ProductoService;

@RestController
public class ProductoController extends CommonController<ProductoRequest, ProductoResponse, ProductoService> {

	public ProductoController(ProductoService service) {
		super(service);
	}
	
	
	
}
