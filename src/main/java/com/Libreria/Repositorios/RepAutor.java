package com.Libreria.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Libreria.entidades.Autor;


@Repository
public interface RepAutor extends JpaRepository<Autor, Integer>{

}
