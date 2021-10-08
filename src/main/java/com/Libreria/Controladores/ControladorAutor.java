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
import com.Libreria.entidades.Autor;

@Controller
@RequestMapping("/autor")
public class ControladorAutor {
	
	@Autowired
	private ServicioAutor servAutor;
	
	@GetMapping("/registro")
	public String registroAutor() {
		return "crearAutor";
	}

	@PostMapping("/registro") //Post: envío info // Acá tiene que ir al registro (cuando haga la acción)
	public String nuevoAutor(@RequestParam("nombre") String nombre) {
		servAutor.crearAutor(nombre);
		return ("redirect:/autor/lista"); // acá crea el autor y le indico que vuelva a mi pág ppal.
	}
	
	@GetMapping("/lista")
	public String listar(ModelMap modelo) {
		List<Autor> listaAut=servAutor.listarAutor();
		modelo.addAttribute("listaAutores",listaAut);
		return "listaAutor.html";
	}
	
	@PostMapping("/lista")
	public String buscarName(@RequestParam String nombre) {
		Autor a = servAutor.findName(nombre);
		System.out.println(a.getNombre()+" con id "+a.getId());
		return "resultado";
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
		
		servAutor.eliminarAutor(id);
		return "redirect:/autor/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String viewEditar() {
			return "editarAutor.html";
	}
	
	@PostMapping("/editar/{id}")
	public String editar(@PathVariable int id,@RequestParam String nombre) {
		servAutor.modificarAutor(id, nombre);
		return "redirect:/autor/lista";
	}
	
}
