package com.Libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Editorial {

	@Id
	private Integer id;
	private String nombre;
	private boolean alta;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isAlta() {
		return alta;
	}
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
	
	
}
