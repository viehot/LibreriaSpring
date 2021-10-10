package com.Libreria.Servicios;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Libreria.Repositorios.RepLibro;

@Service
public class ServicioLibro {
	
	@Autowired
	private RepLibro repLibro;
	
	@Autowired
	private ServicioAutor serviAut;
	
	@Autowired
	private ServicioEditorial servEdit;
	
	
	
	public void validar(long isbn, String titulo, int anio, int ejemplares) throws Exception {
		LocalDate ahora = LocalDate.now();
		
		if (isbn < 1000000) {
			throw new Exception("Error. Debe ingresar 7 digitos numericos.");
		}
		if (titulo == null || titulo.isBlank()) {
			throw new Exception("Error. Ingrese un nombre");
		}
		if (ejemplares < 0) {
			throw new Exception("Error. No puede ser 0 ejemplares");
		}
		if (anio > ahora.getYear()) {
			throw new Exception("Error. El añio no puede ser mayor al actual");
		}
	}
	
	public int randomId() {
		String uuid=UUID.randomUUID().toString(); //Un random
		int id=uuid.hashCode();// paso el código de ese uuid, para que quede un valor más corto
		id=id<0?-id:id; // El hashCode puede ser (-) -- Esto es un if ternario: Si id<0, lo cambia al opuesto. Si no (:) lo mantiene (+)
		return id;
	}

}
