package com.Libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Libro {
	
	@Id
	private Integer id;
	private Long isbn;
	private String titulo;
	private Integer anio;
	private Integer ejemplares;
	private Integer ejemplPrestados;
	private Integer ejemplRestantes;
	private boolean alta;
	@OneToOne
	private Autor autor;
	@OneToOne
	private Editorial editorial;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getEjemplares() {
		return ejemplares;
	}
	public void setEjemplares(Integer ejemplares) {
		this.ejemplares = ejemplares;
	}
	public Integer getEjemplPrestados() {
		return ejemplPrestados;
	}
	public void setEjemplPrestados(Integer ejemplPrestados) {
		this.ejemplPrestados = ejemplPrestados;
	}
	public Integer getEjemplRestantes() {
		return ejemplRestantes;
	}
	public void setEjemplRestantes(Integer ejemplRestantes) {
		this.ejemplRestantes = ejemplRestantes;
	}
	public boolean isAlta() {
		return alta;
	}
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Editorial getEditorial() {
		return editorial;
	}
	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}
	
	
	

}
