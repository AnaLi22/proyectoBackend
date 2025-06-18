package com.proyecto.pedidos.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.proyecto.commons.proyecto.dto.ClienteResponse;
import com.proyecto.commons.proyecto.dto.PedidoRequest;
import com.proyecto.commons.proyecto.dto.PedidoResponse;
import com.proyecto.commons.proyecto.dto.ProductoCantidadRequest;
import com.proyecto.commons.proyecto.dto.ProductoResponse;
import com.proyecto.commons.proyecto.mappers.CommonMapper;
import com.proyecto.commons.proyecto.models.entities.Pedido;
import com.proyecto.commons.proyecto.models.entities.ProductoPedido;
import com.proyecto.pedidos.clients.ClienteClient;
import com.proyecto.pedidos.clients.ProductoClient;

@Component
public class PedidoMapper extends CommonMapper<PedidoRequest, PedidoResponse, Pedido>{

	private ProductoClient productoClient;
	
	private ClienteClient clienteClient;
	
	

	public PedidoMapper(ProductoClient productoClient, ClienteClient clienteClient) {
		this.productoClient = productoClient;
		this.clienteClient = clienteClient;
	}

	public PedidoResponse entityToDTO(Pedido entity) {
	    if (entity == null) {
	        return null;
	    }

	    ClienteResponse cliente = null;
	    if (entity.getIdCliente() != null) {
	        cliente = clienteClient.getCliente(entity.getIdCliente());
	    }

	    List<ProductoPedido> idProductos = entity.getProductos();
	    List<ProductoResponse> productos = new ArrayList<>();

	    if (idProductos != null) {
	        for (ProductoPedido idProducto : idProductos) {
	            ProductoResponse producto = productoClient.getProducto(idProducto.getIdProducto());
	            if (producto != null) {
	                productos.add(producto);
	            }
	        }
	    }

	    return new PedidoResponse(
	            entity.getId(),
	            cliente,
	            entity.getTotal(),
	            entity.getFechaCreacion(),
	            entity.getIdEstado(),
	            productos
	    );
	}


	@Override
	public Pedido dtoToEntity(PedidoRequest request) {
	    if (request != null) {
	        Pedido pedidoEntity = new Pedido();
	        pedidoEntity.setIdCliente(request.idCliente());

	        List<ProductoPedido> productos = new ArrayList<>();
	        if (request.productos() != null && !request.productos().isEmpty()) {
	            for (ProductoCantidadRequest pc : request.productos()) {
	                if (pc != null && pc.idProducto() != null) {
	                    ProductoResponse productoResponse = productoClient.getProducto(pc.idProducto());

	                    if (productoResponse != null) {
	                        ProductoPedido producto = new ProductoPedido();
	                        producto.setIdProducto(productoResponse.id());
	                        producto.setNombre(productoResponse.nombre());
	                        producto.setDescripcion(productoResponse.descripcion());
	                        producto.setPrecio(productoResponse.precio());
	                        producto.setCantidad(pc.cantidad());

	                        productos.add(producto);
	                    }
	                }
	            }
	        }

	        pedidoEntity.setProductos(productos);
	        return pedidoEntity;
	    }
	    return null;
	}

	
}
