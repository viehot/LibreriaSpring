package com.Libreria.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Libreria.entidades.Editorial;

@Repository
public interface RepEditorial extends JpaRepository<Editorial, Integer>{
	
	@Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
	public Editorial findEditorialName(@Param("nombre") String nombre);
}
