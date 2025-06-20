package com.proyecto.pedidos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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

	    if (pedido.getProductos() != null) {
	        for (ProductoPedido producto : pedido.getProductos()) {
	            double subtotal = producto.getPrecio() * producto.getCantidad();
	            total += subtotal;
	        }
	    }

	    pedido.setTotal(total);
	    
	    pedido.setIdEstado(1L);

	    Pedido pedidoGuardado = repository.save(pedido);
	    return mapper.entityToDTO(pedidoGuardado);
	}


	@Override
	@Transactional
	public PedidoResponse actualizar(PedidoRequest request, Long id) {
	    Optional<Pedido> pedidoOptional = repository.findById(id);

	    if (pedidoOptional.isEmpty()) {
	        throw new NoSuchElementException("Pedido con ID " + id + " no encontrado.");
	    }

	    Pedido pedidoExistente = pedidoOptional.get();
	    Long estadoActual = pedidoExistente.getIdEstado();

	    if (estadoActual == 3 || estadoActual == 4) {
	        throw new IllegalStateException("No se puede editar un pedido entregado o cancelado.");
	    }

	    Long nuevoEstado = request.idEstado();
	    
	    if (nuevoEstado < estadoActual) {
	        throw new IllegalStateException("No se puede cambiar a un estado anterior.");
	    }

	    if (nuevoEstado != 2 && nuevoEstado != 3) {
	        throw new IllegalArgumentException("Solo se puede cambiar el estado a 'Enviado' (2) o 'Entregado' (3).");
	    }

	    

	    pedidoExistente.setIdEstado(nuevoEstado);
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

	    if (pedido.getIdEstado() != null && pedido.getIdEstado() == 4) {
	        throw new IllegalStateException("El pedido ya se encuentra cancelado.");
	    }

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
