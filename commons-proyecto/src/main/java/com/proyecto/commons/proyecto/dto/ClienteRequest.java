package com.proyecto.commons.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
		
		@NotBlank(message = "El nombre no puede ser vacío ni nulo.")
		String nombre,
		@NotBlank(message = "El apellido no puede ser vacío ni nulo.")
		String apellido,
		@NotBlank(message = "El email no puede ser vacío ni nulo.")
		@Email(message = "El formato para el Correo Electrónico no es válido (Ej: ejemplo@dominio.com)")
		@Pattern(
			    regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
			    message = "El correo debe estar en minúsculas y tener un formato válido (ejemplo@dominio.com)."
			)		
		String email,
		
		@NotBlank(message = "El telefono no puede ser vacío ni nulo.")
		@Pattern(regexp = "\\d{10}", message = "El teléfono debe contener exactamente 10 dígitos.")
		String telefono,
		@Size(max =100, message = "No puedes escribir más.")
		String direccion
		) {
}	