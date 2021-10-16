package com.Libreria.entidades;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Prestamo implements Serializable {


	private static final long serialVersionUID = 2003747962041619905L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private Boolean alta;
	private Libro libro;
	private Usuario usuario;
	
}
