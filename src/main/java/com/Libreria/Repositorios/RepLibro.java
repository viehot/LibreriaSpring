package com.Libreria.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Libreria.entidades.Libro;

@Repository
public interface RepLibro extends JpaRepository<Libro, Integer>{

}
