package com.proyecto.pedidos.services;

import com.proyecto.commons.proyecto.dto.PedidoRequest;
import com.proyecto.commons.proyecto.dto.PedidoResponse;
import com.proyecto.commons.proyecto.services.CommonService;

public interface PedidoService extends CommonService<PedidoRequest, PedidoResponse>{
	
	void validarClientes(PedidoRequest request);
}
