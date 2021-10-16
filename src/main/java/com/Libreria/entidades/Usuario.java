package com.Libreria.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.Libreria.Enums.Rol;

import lombok.Data;

@Data
@Entity
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	private String nombre;
	private String apellido;
	private String telefono;
	private Boolean alta;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Rol rol;
}
