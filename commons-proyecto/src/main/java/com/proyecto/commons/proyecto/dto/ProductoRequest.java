package com.proyecto.commons.proyecto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoRequest(
    @NotBlank(message = "El nombre no puede ser nulo o vacío")
    String nombre, 

    @NotBlank(message = "La descripción no puede ser nula o vacía")
    String descripcion, 

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio no puede ser negativo")
    Double precio, 

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    Integer stock
) {}
