package com.Libreria.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Libreria.Servicios.ServicioAutor;
import com.Libreria.entidades.Autor;

@Controller
@RequestMapping("/autor")
public class ControladorAutor {
	
	@Autowired
	private ServicioAutor servAutor;

	@PostMapping("/registro") //Post: envío info // Acá tiene que ir al registro (cuando haga la acción)
	public String nuevoAutor(@RequestParam("nombre") String nombre) {
		servAutor.crearAutor(nombre);
		return ("redirect:/"); // acá crea el autor y le indico que vuelva a mi pág ppal.
	}
	
	@GetMapping("/lista")
	public String listar(ModelMap modelo) {
		List<Autor> listaAut=servAutor.listarAutor();
		modelo.addAttribute("listaAutores",listaAut);
		return "listaAutor.html";
	}
	
}
