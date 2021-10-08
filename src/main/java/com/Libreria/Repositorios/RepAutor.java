package com.Libreria.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Libreria.entidades.Autor;


@Repository
public interface RepAutor extends JpaRepository<Autor, Integer>{

	
	@Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
	public Autor findAutorName(@Param("nombre") String nombre);
}
