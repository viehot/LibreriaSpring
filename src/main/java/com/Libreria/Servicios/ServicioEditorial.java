package com.Libreria.Servicios;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Libreria.Repositorios.RepEditorial;
import com.Libreria.entidades.Editorial;

@Service
public class ServicioEditorial {
	@Autowired
	private RepEditorial repEdi; //Es como una instancia del Repositorio de Autor (siempre que se inicialice el proyecto)
	
	
	public void crearEditorial(String nombre) throws Exception {
		
		validar(nombre);
		
		Editorial a=new Editorial();
		a.setNombre(nombre);
		a.setId(randomId());
		a.setAlta(true);
		
		//Para subir al servidor:
		repEdi.save(a);
	}
	
	public void eliminarEditorial(Integer id) {
		repEdi.deleteById(id);
	}
	
	public void modificarEditorial(Integer id,String nombre) throws Exception {
		
		validar(nombre);
		
		Editorial a=repEdi.findById(id).get();
		a.setNombre(nombre);
		repEdi.save(a);
	}
	
	public List<Editorial> listarEditorial(){
		List<Editorial> listaEditorial=repEdi.findAll();
		return listaEditorial;
	}
	
	public Editorial listEdit(int id) {
		Editorial e = repEdi.getById(id);
		return e;
	}
	
	public Editorial findName(String nombre) {
		Editorial a = repEdi.findEditorialName(nombre);
		return a;
	}
	
	public void validar(String nombre) throws Exception {
		if (nombre == null || nombre.isEmpty()) {
			throw new Exception();
		}
	}
	
	public int randomId() {
		String uuid=UUID.randomUUID().toString(); //Un random
		int id=uuid.hashCode();// paso el código de ese uuid, para que quede un valor más corto
		id=id<0?-id:id; // El hashCode puede ser (-) -- Esto es un if ternario: Si id<0, lo cambia al opuesto. Si no (:) lo mantiene (+)
		return id;
	}
}
