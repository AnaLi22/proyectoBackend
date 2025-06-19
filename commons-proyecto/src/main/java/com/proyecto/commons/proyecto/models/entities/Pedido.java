package com.proyecto.commons.proyecto.models.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDOS_SEQ")
	@SequenceGenerator(name = "PEDIDOS_SEQ", sequenceName = "PEDIDOS_SEQ", allocationSize = 1)
	@Column(name = "ID_PEDIDO")
	private Long id;
	
	@Column(name = "ID_CLIENTE", nullable = false)
	private Long idCliente;
	/*
	@Column(name = "ID_PRODUCTO", nullable = false)
	private Long idProducto;
	*/
	@Column(name = "TOTAL", nullable = false)
	private Double total;
	
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "ID_ESTADO", nullable = false)
	private Long idEstado;
	
	@ElementCollection
	@CollectionTable(name = "PRODUCTOS_PEDIDO", joinColumns = @JoinColumn(name = "ID_PEDIDO"))
	private List<ProductoPedido> productos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long i) {
		this.idEstado = i;
	}

	public List<ProductoPedido> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoPedido> productos) {
		this.productos = productos;
	}
	
	

}
