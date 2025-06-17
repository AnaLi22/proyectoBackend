package com.proyecto.pedidos.mapper;

import org.springframework.stereotype.Component;

import com.proyecto.commons.proyecto.dto.PedidoRequest;
import com.proyecto.commons.proyecto.dto.PedidoResponse;
import com.proyecto.commons.proyecto.mappers.CommonMapper;
import com.proyecto.commons.proyecto.models.entities.Pedido;

@Component
public class PedidoMapper extends CommonMapper<PedidoRequest, PedidoResponse, Pedido>{

	@Override
	public PedidoResponse entityToDTO(Pedido entity) {
		/*
		if (entity != null) {
			PedidoResponse response = new PedidoResponse(entity.getId(), , null, null, null, null)
		}
		*/
		return null;
	}

	@Override
	protected Pedido dtoToEntity(PedidoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
