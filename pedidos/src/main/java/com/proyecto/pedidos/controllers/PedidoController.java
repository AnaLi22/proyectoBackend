package com.proyecto.pedidos.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.proyecto.commons.proyecto.controllers.CommonController;
import com.proyecto.commons.proyecto.dto.PedidoRequest;
import com.proyecto.commons.proyecto.dto.PedidoResponse;
import com.proyecto.pedidos.services.PedidoService;

@RestController
public class PedidoController extends CommonController<PedidoRequest, PedidoResponse, PedidoService>{

	public PedidoController(PedidoService service) {
		super(service);
	}

	
}
