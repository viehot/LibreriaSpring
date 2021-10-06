package com.Libreria.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Libreria.entidades.Editorial;

@Repository
public interface RepEditorial extends JpaRepository<Editorial, Integer>{

}
