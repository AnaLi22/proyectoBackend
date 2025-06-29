package com.proyecto.clientes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.clientes.mappers.ClienteMapper;
import com.proyecto.clientes.models.repository.ClienteRepository;
import com.proyecto.commons.proyecto.dto.ClienteRequest;
import com.proyecto.commons.proyecto.dto.ClienteResponse;
import com.proyecto.commons.proyecto.models.entities.Cliente;


@Service
public class ClienteServiceImpl implements ClienteService{

	private ClienteRepository repository;
	
	private ClienteMapper mapper;

	
	public ClienteServiceImpl(ClienteRepository repository, ClienteMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ClienteResponse> listar() {
		List<ClienteResponse> clientes = new ArrayList<>();
		repository.findAll().forEach( cliente -> {
			clientes.add(mapper.entityToDTO(cliente));
			
		});
	return clientes;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Optional<ClienteResponse> obtenerPorId(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			return Optional.of(mapper.entityToDTO(cliente.get()));	
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public ClienteResponse insertar(ClienteRequest request) {
		Cliente cliente = mapper.dtoToEntity(request);
		return mapper.entityToDTO(repository.save(cliente));
	}

	@Override
	@Transactional
	public ClienteResponse actualizar(ClienteRequest request, Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			Cliente clienteDb = cliente.get();
			clienteDb.setId(id);
			clienteDb.setNombre(request.nombre());
			clienteDb.setApellido(request.apellido());
			clienteDb.setEmail(request.email());
			clienteDb.setTelefono(request.telefono());
			clienteDb.setDireccion(request.direccion());
			return mapper.entityToDTO(repository.save(clienteDb));
			
		}else {
			throw new NoSuchElementException();
		}
		
	}
	

	@Override
	@Transactional
	public ClienteResponse eliminar(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			repository.deleteById(id);
			return mapper.entityToDTO(cliente.get());
			
		}else {
			throw new NoSuchElementException();
		}
	}
	
	
	@Override
	public void validarClientes(ClienteRequest request) {
		
	}

	
	
}
