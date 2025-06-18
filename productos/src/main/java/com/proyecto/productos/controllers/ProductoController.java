package com.proyecto.productos.controllers;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.productos.dto.ProductoRequest;
import com.proyecto.productos.dto.ProductoResponse;
import com.proyecto.productos.services.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
	
	private  ProductoService service;

	public ProductoController(ProductoService service) {
	
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductoResponse>> listar(){
		return ResponseEntity.ok(service.listar());
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductoResponse> obtenerporId(@PathVariable Long id){
		Optional<ProductoResponse> opt = service.obtenerPorId(id);
		if (opt.isPresent()) {
			return ResponseEntity.ok(opt.get());
			
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<ProductoResponse> insertar(@RequestBody ProductoRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insertar(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductoResponse> actualizar(@RequestBody ProductoRequest request, @PathVariable Long id){
		ProductoResponse response = service.actualizar(request, id);
		if (response != null) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductoResponse> eliminar (@PathVariable Long id){
		ProductoResponse response = service.eliminar(id);
		if (response != null) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}
	
}
