package com.Libreria.Servicios;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Libreria.Repositorios.RepAutor;
import com.Libreria.entidades.Autor;

@Service
public class ServicioAutor {

	@Autowired
	private RepAutor repAutor; //Es como una instancia del Repositorio de Autor (siempre que se inicialice el proyecto)
	
	
	public void crearAutor(String nombre) {
		Autor a=new Autor();
		a.setNombre(nombre);
		a.setId(randomId());
		a.setAlta(true);
		
		//Para subir al servidor:
		repAutor.save(a);
	}
	
	public void eliminarAutor(Integer id) {
		repAutor.deleteById(id);
	}
	
	public void modificarAutor(Integer id,String nombre) {
		Autor a=repAutor.findById(id).get();
		a.setNombre(nombre);
		repAutor.save(a);
	}
	
	public List<Autor> listarAutor(){
		List<Autor> listaAutores=repAutor.findAll();
		return listaAutores;
	}
	
	public Autor listAut(int id) {
		Autor a = repAutor.getById(id);
		return a;
	}
	
	public Autor findName(String nombre) {
		Autor a = repAutor.findAutorName(nombre);
		return a;
	}
	
	public int randomId() {
		String uuid=UUID.randomUUID().toString(); //Un random
		int id=uuid.hashCode();// paso el código de ese uuid, para que quede un valor más corto
		id=id<0?-id:id; // El hashCode puede ser (-) -- Esto es un if ternario: Si id<0, lo cambia al opuesto. Si no (:) lo mantiene (+)
		return id;
	}
	
	
}
