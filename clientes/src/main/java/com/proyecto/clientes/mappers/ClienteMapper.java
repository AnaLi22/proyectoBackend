package com.proyecto.clientes.mappers;

import org.springframework.stereotype.Component;

import com.proyecto.commons.proyecto.dto.ClienteRequest;
import com.proyecto.commons.proyecto.dto.ClienteResponse;
import com.proyecto.commons.proyecto.mappers.CommonMapper;
import com.proyecto.commons.proyecto.models.entities.Cliente;

@Component
public class ClienteMapper extends CommonMapper<ClienteRequest, ClienteResponse, Cliente>{

	@Override
	public ClienteResponse entityToDTO(Cliente entity) {
		if (entity != null) {
			ClienteResponse response = new ClienteResponse(
					entity.getId(),
					entity.getNombre(),
					entity.getApellido(),
					entity.getEmail(),
					entity.getTelefono(),
					entity.getDireccion());
			return response;	
		}
		return null;			
	}

	@Override
	public Cliente dtoToEntity(ClienteRequest request) {
		if (request != null) {
			Cliente cliente = new Cliente();
			cliente.setNombre(request.nombre());
			cliente.setApellido(request.apellido());
			cliente.setEmail(request.email());
			cliente.setTelefono(request.telefono());
			cliente.setDireccion(request.direccion());
			return cliente;
		}
		return null;
	}

	

}
