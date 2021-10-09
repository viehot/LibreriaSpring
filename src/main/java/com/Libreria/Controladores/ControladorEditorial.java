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
		model.put("lista", "Lista de Editoriales");
		return "/editorial/listaEditorial";
	}
	
	@GetMapping("/registro")
	public String regEditorial(ModelMap model) {
		model.put("registro", "Registro de Editorial");
		return "/editorial/listaEditorial";
	}
	
	@PostMapping("/registro")
	public String registro(ModelMap model,@RequestParam String nombre) {
		try {
			servi.crearEditorial(nombre);
			return "redirect:/editorial/lista";
		} catch (Exception e) {
			model.put("error", "Error al cargar nombre");
			return regEditorial(model);
		}
		
		
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
		model.put("editar", "Editar Editorial");
		return "/editorial/listaEditorial";
	}
	
	@PostMapping("/editar/{id}")
	public String editar(ModelMap model,@PathVariable int id,@RequestParam String nombre) {
		try {
			servi.modificarEditorial(id, nombre);
			return "redirect:/editorial/lista";
		} catch (Exception e) {
			model.put("error", "Error al cargar nombre");
			return viewEditar(model, id);
		}
		
		
	}
}
