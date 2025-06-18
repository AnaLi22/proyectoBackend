package com.proyecto.productos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.commons.proyecto.dto.ProductoRequest;
import com.proyecto.commons.proyecto.dto.ProductoResponse;
import com.proyecto.commons.proyecto.models.entities.Producto;
import com.proyecto.productos.mapper.ProductoMapper;
import com.proyecto.productos.models.repositories.ProductoRepository;


@Service
public class ProductoServiceImpl implements ProductoService{

	
	private ProductoRepository repository;
	
	private ProductoMapper mapper;

	public ProductoServiceImpl(ProductoRepository repository, ProductoMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductoResponse> listar() {
		List<ProductoResponse> productos = new ArrayList<>();
		repository.findAll().forEach( producto ->{
			productos.add(mapper.entityToDTO(producto));
		});
		return productos;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductoResponse> obtenerPorId(Long id) {
		Optional<Producto> producto = repository.findById(id);
		if (producto.isPresent()) {
			return Optional.of(mapper.entityToDTO(producto.get()));	
		}
		return Optional.empty();		
	}

	@Override
	@Transactional
	public ProductoResponse insertar(ProductoRequest request) {
		Producto producto = mapper.dtoToEntity(request);
		return mapper.entityToDTO(repository.save(producto));	
	}

	@Override
	@Transactional
	public ProductoResponse actualizar(ProductoRequest request, Long id) {
		Optional<Producto> producto = repository.findById(id);
		if (producto.isPresent()) {
			Producto productoDb = producto.get();
			productoDb.setId(id);
			productoDb.setNombre(request.nombre());
			productoDb.setDescripcion(request.descripcion());
			productoDb.setPrecio(request.precio());
			productoDb.setStock(request.stock());
			return mapper.entityToDTO(repository.save(productoDb));
		}
		return null;
	}

	@Override
	@Transactional
	public ProductoResponse eliminar(Long id) {
		Optional<Producto> producto = repository.findById(id);
		if (producto.isPresent()) {
			repository.deleteById(id);
			return mapper.entityToDTO(producto.get());
		}
		return null;
	}

}
