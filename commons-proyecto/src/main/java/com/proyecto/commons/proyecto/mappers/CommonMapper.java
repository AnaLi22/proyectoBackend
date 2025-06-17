package com.proyecto.commons.proyecto.mappers;

public abstract class CommonMapper <RQ, RS, E> {

	protected abstract RS entityToDTO(E entity);

	protected abstract E dtoToEntity(RQ request);	

}
