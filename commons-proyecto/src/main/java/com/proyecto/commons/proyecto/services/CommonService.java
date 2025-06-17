package com.proyecto.commons.proyecto.services;

import java.util.List;
import java.util.Optional;

public interface CommonService <RQ, RS> {
	
	List<RS> listar();
	
	Optional<RS> obtenerPorId(Long id);
	
	RS insertar(RQ request);
	
	RS actualizar (RQ request, Long id);
	
	RS eliminar(Long id);

}
