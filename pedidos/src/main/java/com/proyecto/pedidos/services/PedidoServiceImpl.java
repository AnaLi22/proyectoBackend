package com.proyecto.pedidos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.commons.proyecto.dto.PedidoRequest;
import com.proyecto.commons.proyecto.dto.PedidoResponse;
import com.proyecto.commons.proyecto.dto.ProductoCantidadRequest;
import com.proyecto.commons.proyecto.models.entities.Pedido;
import com.proyecto.commons.proyecto.models.entities.ProductoPedido;
import com.proyecto.pedidos.clients.ClienteClient;
import com.proyecto.pedidos.clients.ProductoClient;
import com.proyecto.pedidos.mapper.PedidoMapper;
import com.proyecto.pedidos.models.repositories.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	private ClienteClient clienteClient;
	
	private ProductoClient productoClient;
	
	private PedidoRepository repository;
	
	private PedidoMapper mapper;

	public PedidoServiceImpl(ClienteClient clienteClient, ProductoClient productoClient, PedidoRepository repository,
			PedidoMapper mapper) {
		this.clienteClient = clienteClient;
		this.productoClient = productoClient;
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PedidoResponse> listar() {
		List<PedidoResponse> pedidos = new ArrayList<>();
		repository.findAll().forEach( pedido -> {
			pedidos.add(mapper.entityToDTO(pedido));
		});
		return pedidos;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PedidoResponse> obtenerPorId(Long id) {
		Optional<Pedido> pedido =repository.findById(id);
		if (pedido.isPresent()) {
			return Optional.of(mapper.entityToDTO(pedido.get()));
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public PedidoResponse insertar(PedidoRequest request) {
	    Pedido pedido = mapper.dtoToEntity(request);

	    Date fechaActual = new Date();
	    pedido.setFechaCreacion(fechaActual);

	    double total = 0.0;

	    if (pedido.getProductos() != null && !pedido.getProductos().isEmpty()) {
	        for (ProductoPedido producto : pedido.getProductos()) {
	            double subtotal = producto.getPrecio() * producto.getCantidad();
	            total += subtotal;
	        }
	    }

	    pedido.setTotal(total);

	    Pedido pedidoGuardado = repository.save(pedido);
	    return mapper.entityToDTO(pedidoGuardado);
	}


	@Override
	@Transactional
	public PedidoResponse actualizar(PedidoRequest request, Long id) {
	    Optional<Pedido> pedido = repository.findById(id);

	    if (pedido.isPresent()) {
	        return null;
	    }

	    Pedido pedidoExistente = pedido.get();

	    // No permitir actualizar si el pedido ya fue entregado o cancelado
	    if (pedidoExistente.getIdEstado() != null && 
	        (pedidoExistente.getIdEstado() == 3 || pedidoExistente.getIdEstado() == 4)) {
	        throw new IllegalStateException("No se puede editar un pedido entregado o cancelado.");
	    }

	    // Guardar cambios
	    Pedido actualizado = repository.save(pedidoExistente);
	    return mapper.entityToDTO(actualizado);
	}


	@Override
	@Transactional
	public PedidoResponse eliminar(Long id) {
	    Optional<Pedido> pedidoOptional = repository.findById(id);

	    if (pedidoOptional.isEmpty()) {
	        return null;
	    }

	    Pedido pedido = pedidoOptional.get();

	    // Si ya está cancelado, no hacemos nada
	    if (pedido.getIdEstado() != null && pedido.getIdEstado() == 4) {
	        throw new IllegalStateException("El pedido ya se encuentra cancelado.");
	    }

	    // Cambiar estado a cancelado (ID = 4)
	    pedido.setIdEstado(4L);

	    Pedido pedidoCancelado = repository.save(pedido);
	    return mapper.entityToDTO(pedidoCancelado);
	}


	@Override
	public void validarClientes(PedidoRequest request) {
	    if (request.idCliente() != null) {
	        clienteClient.getCliente(request.idCliente());
	    }

	    if (request.productos() != null && !request.productos().isEmpty()) {
	        for (ProductoCantidadRequest producto : request.productos()) {
	            if (producto.idProducto() != null) {
	                productoClient.getProducto(producto.idProducto());
	            }
	        }
	    }
	}

	
}
