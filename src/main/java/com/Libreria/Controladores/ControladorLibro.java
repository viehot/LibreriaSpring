package com.Libreria.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Libreria.Servicios.ServicioAutor;
import com.Libreria.Servicios.ServicioEditorial;
import com.Libreria.Servicios.ServicioLibro;
import com.Libreria.entidades.Autor;
import com.Libreria.entidades.Editorial;
import com.Libreria.entidades.Libro;

@Controller
@RequestMapping("/libro")
public class ControladorLibro {
	
	@Autowired
	private ServicioLibro serLib;
	
	@Autowired
	private ServicioAutor serAut;
	
	@Autowired
	private ServicioEditorial serEdit;
	
	@GetMapping("/lista")
	public String lista(ModelMap model) {
		model.put("lista", "Lista de Libros");
		List<Libro> lib = serLib.listarLibros();
		model.addAttribute("libro", lib);
		return "/libro/listarLibro";
	}
	
	@GetMapping("/registro")
	public String registro(ModelMap model) {
		model.put("registro", "Regisro de Libros");
		List<Autor> aut = serAut.listarAutor();
		List<Editorial> edit = serEdit.listarEditorial();
		model.addAttribute("autor", aut);
		model.addAttribute("editorial", edit);
		return "/libro/listarLibro";
		
	}
}
