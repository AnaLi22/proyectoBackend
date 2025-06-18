package com.proyecto.productos.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.commons.proyecto.models.entities.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
