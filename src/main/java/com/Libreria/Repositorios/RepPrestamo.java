package com.Libreria.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Libreria.entidades.Prestamo;

@Repository
public interface RepPrestamo extends JpaRepository<Prestamo, Long> {

}
