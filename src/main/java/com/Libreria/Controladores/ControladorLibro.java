package com.Libreria.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Libreria.Servicios.ServicioLibro;
import com.Libreria.entidades.Libro;

@Controller
@RequestMapping("/libro")
public class ControladorLibro {
	
	@Autowired
	private ServicioLibro serLib;
	
	@GetMapping("/lista")
	public String lista(ModelMap model) {
		model.put("lista", "Lista de Libros");
		List<Libro> lib = serLib.listarLibros();
		model.addAttribute("libro", lib);
		return "/libro/listarLibro";
	}
}
