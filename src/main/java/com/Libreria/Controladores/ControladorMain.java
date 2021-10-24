package com.Libreria.Controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
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
	
	
	@PostMapping("/registro/usuario")
	public String regUser(ModelMap model,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String telefono,@RequestParam String email,@RequestParam String password) {
		try {
			servicioUsuario.registroUsuario(nombre, apellido, telefono, email, password);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "registro";
		}
		
		return "login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
		
	}
	
	@GetMapping("/loginsucces")
	public String loginsucces() {
		return "redirect:/";
	}
	
	


}
