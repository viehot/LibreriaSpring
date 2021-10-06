package com.Libreria.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/") //Primera vista - Siempre se indica así // Es la posición de partida
public class ControladorMain {
	
	@GetMapping("/")
	public String index() {
		return "index.html"; // El html es un String, por eso el método devuelve String
	}
	
	@GetMapping("/autor/registro")
	public String autor() {
		return "crearAutor.html";
	}

}
