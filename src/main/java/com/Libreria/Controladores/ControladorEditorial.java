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

import com.Libreria.Servicios.ServicioEditorial;
import com.Libreria.entidades.Editorial;


@Controller
@RequestMapping("/editorial")
public class ControladorEditorial {

	@Autowired
	private ServicioEditorial servi;
	
	@GetMapping("/lista")
	public String listEditorial(ModelMap model) {
		List<Editorial> edi = servi.listarEditorial();
		model.addAttribute("edito", edi);
		return "listaEditorial";
	}
	
	@GetMapping("/registro")
	public String regEditorial() {
		return "crearEditorial";
	}
	
	@PostMapping("/registro")
	public String registro(@RequestParam String nombre) {
		servi.crearEditorial(nombre);
		return "redirect:/editorial/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
		servi.eliminarEditorial(id);
		return "redirect:/editorial/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String viewEditar(ModelMap model,@PathVariable int id) {
		Editorial edi = servi.listEdit(id);
		model.addAttribute("edito", edi);
		return "editarEditorial";
	}
	
	@PostMapping("/editar/{id}")
	public String editar(@PathVariable int id,@RequestParam String nombre) {
		servi.modificarEditorial(id, nombre);
		return "redirect:/editorial/lista";
	}
}
