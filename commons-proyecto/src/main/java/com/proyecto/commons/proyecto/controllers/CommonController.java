package com.proyecto.commons.proyecto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.commons.proyecto.services.CommonService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
public class CommonController <RQ, RS, S extends CommonService<RQ, RS>> {
	
	protected S service;

	public CommonController(S service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<RS>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RS> obtenerPorId(@PathVariable @Min(value = 1, message = "El ID debe ser mayor que 0") Long id) {
		return service.obtenerPorId(id).map(ResponseEntity::ok).orElseThrow(NoSuchElementException::new);
	}

	@PostMapping
	public ResponseEntity<?> insertar(@Valid @RequestBody RQ request, BindingResult result) {
		if (result.hasErrors()) {
			return this.validate(result);
		}
		return ResponseEntity.ok(service.insertar(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody RQ request, 
			@PathVariable @Min(value = 1, message = "El ID debe ser mayor que 0") Long id, BindingResult result) {
		if (result.hasErrors()) {
			return this.validate(result);
		}
		RS response = service.actualizar(request, id);
		if (response != null) {
			return ResponseEntity.ok(response);
		}
		throw new NoSuchElementException();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable @Min(value = 1, message = "El ID debe ser mayor que 0") Long id) {
		RS response = service.eliminar(id);
		if (response != null) {
			return ResponseEntity.ok(response);
		}
		throw new NoSuchElementException();
	}
	
	protected ResponseEntity<?> validate(BindingResult result){
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(error -> {
			errores.put(error.getField(), error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
	
	
	
}
