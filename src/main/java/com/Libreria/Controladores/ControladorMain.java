package com.Libreria.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Libreria.Servicios.ServicioUsuario;



@Controller
@RequestMapping("/") //Primera vista - Siempre se indica así // Es la posición de partida
public class ControladorMain {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@GetMapping("/")
	public String index() {
		return "index.html"; // El html es un String, por eso el método devuelve String
	}
	
	@GetMapping("/registro/usuario")
	public String registroUser() {
		return "registro.html";
	}
	
	@GetMapping("/registro/admin")
	public String registroAdmin() {
		return "registro";
	}
	
	@PostMapping("/registro/usuario")
	public String regUser(ModelMap model,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String telefono,@RequestParam String email,@RequestParam String password) {
		servicioUsuario.registroUsuario(nombre, apellido, telefono, email, password);
		return "redirect:/";
	}
	
	@PostMapping("/registro/admin")
	public String regAdmin(ModelMap model,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String telefono,@RequestParam String email,@RequestParam String password) {
		servicioUsuario.registroAdmin(nombre, apellido, telefono, email, password);
		return "redirect:/";
	}


}
