package com.proyecto.commons.proyecto.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table (name = "PRODUCTOS")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOS_SEQ")
	@SequenceGenerator(name = "PRODUCTOS_SEQ", sequenceName = "PRODUCTOS_SEQ", allocationSize = 1)
	@Column(name = "ID_PRODUCTO")
	private Long id;
	@Column(name = "NOMBRE", nullable = false)          				//Valida que el campo no debe estar vacio
	private String nombre;
	@Column(name = "DESCRIPCION", nullable = false)						//Valida que el campo no debe estar vacio
	private String descripcion;
	@Column(name = "PRECIO")
	@Min(value = 0, message = "El precio no puede ser negativo") 		//Valida que el precio no puede ser negativo
	private Double precio;

	@Column(name = "STOCK")
	@Min(value = 0, message = "El stock no puede ser negativo")			//Valida que el stock no puede ser negativo
	private Integer stock;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}


	