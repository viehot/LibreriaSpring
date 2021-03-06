package com.Libreria.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
		model.put("isbn", 0);
		return "/libro/listarLibro";

	}

	@PostMapping("/registro")
	public String regisLibro(ModelMap model, @RequestParam String isbn, @RequestParam String titulo,
			@RequestParam String anio, @RequestParam String ejemplares, @RequestParam String autor,
			@RequestParam String editorial) {
		
		try {
			serLib.registroLibro(isbn, titulo, anio, ejemplares, autor, editorial);
			return "redirect:/libro/lista";
		} catch (Exception e) {
			model.put("error", e.getMessage());
			model.put("isbn", isbn);
			model.put("titulo", titulo);
			model.put("anio", anio);
			model.put("ejemplares", ejemplares);
			model.put("autores", autor);
			model.put("edito", editorial);
			return registro(model);
		}
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
		serLib.eliminarLibro(id);
		return "redirect:/libro/lista";
	}

	@GetMapping("/editar/{id}")
	public String editar(ModelMap model, @PathVariable int id) {
		model.put("editar", "Editar Libro");
		Libro lib = serLib.listLibro(id);
		List<Autor> aut = serAut.listarAutor();
		List<Editorial> edit = serEdit.listarEditorial();
		model.addAttribute("libro", lib);
		model.addAttribute("autor", aut);
		model.addAttribute("editorial", edit);
		return "/libro/listarLibro";
	}

	@PostMapping("/editar/{id}")
	public String editarLib(ModelMap model, @PathVariable int id, @RequestParam String isbn,
			@RequestParam String titulo, @RequestParam String anio, @RequestParam String ejemplares,
			@RequestParam String autor, @RequestParam String editorial) {
		try {
			serLib.editarLibro(id, isbn, titulo, anio, ejemplares, autor, editorial);
			return "redirect:/libro/lista";

		} catch (NumberFormatException e) {
			model.put("error", "Debe ingresar un numero");
			return editar(model, id);

		} catch (Exception e) {
			model.put("error", e.getMessage());
			return editar(model, id);
		}
	}

}
