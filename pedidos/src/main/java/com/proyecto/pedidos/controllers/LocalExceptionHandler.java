package com.proyecto.pedidos.controllers;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LocalExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(LocalExceptionHandler.class.getName());
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, Object> > dataIntegrityViolationException(DataIntegrityViolationException e){
		logger.log(Level.SEVERE, "Error en la integridad de los datos: " + (e.getCause() != null ? e.getCause() : e.getMessage()));
		return ResponseEntity.badRequest().body(Map.of(
				"codigo", 400,
				"response", "Error en la integridad de los datos: " + e.getMessage()
				));
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object> > constraintIntegrityViolationException(ConstraintViolationException e){
		logger.log(Level.WARNING, "Violacion de restriccion: " + (e.getCause() != null ? e.getCause() : e.getMessage()));
		return ResponseEntity.badRequest().body(Map.of(
				"codigo", 400,
				"response", "Violacion de restriccion: " + e.getMessage()
				));
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Map<String, Object> > noSuchElementException(NoSuchElementException e){
		logger.log(Level.INFO, "No se encontro informacion asociada con el identificador ingresado");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
				"codigo", 404,
				"response", "No se encontro informacion asociada con el identificador ingresado, verifica que sea correcto"
				));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object> > handleGeneralErrors(Exception e){
		logger.log(Level.SEVERE, "Error interno del servidor: " + (e.getCause() != null ? e.getCause() : e.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
				"codigo", 500,
				"response", "Error interno del servidor: " + (e.getCause() != null ? e.getCause() : e.getMessage())
				));
	}
	
}
