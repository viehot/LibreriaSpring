package com.Libreria.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Libreria.Enums.Rol;
import com.Libreria.Repositorios.RepUsuario;
import com.Libreria.entidades.Usuario;

@Service
public class ServicioUsuario implements UserDetailsService {

	@Autowired
	private RepUsuario repUsuario;

	public void registroUsuario(String nombre, String apellido, String telefono, String email, String password)
			throws Exception {

		validar(nombre, apellido, telefono, email, password);

		Usuario usuario = new Usuario();

		BCryptPasswordEncoder bcript = new BCryptPasswordEncoder();

		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setTelefono(telefono);
		usuario.setEmail(email);
		usuario.setPassword(bcript.encode(password));
		usuario.setAlta(true);
		usuario.setRol(Rol.USUARIO);

		repUsuario.save(usuario);

	}

	public void validar(String nombre, String apellido, String telefono, String email, String password)
			throws Exception {

		if (repUsuario.findByEmail(email) != null) {
			throw new Exception("El mail ingresado ya esta en uso.");
		}

		if (email == null || email.isBlank()) {
			throw new Exception("Debe completar el campo mail");
		}
		if (nombre == null || nombre.isBlank()) {
			throw new Exception("Debe completar el campo nombre");
		}
		if (apellido == null || apellido.isBlank()) {
			throw new Exception("Debe completar el campo apellido");
		}
		if (telefono == null || telefono.isBlank()) {
			throw new Exception("Debe completar el campo telefono");
		}
		if (password == null || password.isBlank() || password.length() > 6) {
			throw new Exception("Debe ingresar una contraseña de mas de 6 digitos");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repUsuario.findByEmail(email);
		User user = null;
		if (usuario != null) {
			List<GrantedAuthority> permisos = new ArrayList<>();
			GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
			permisos.add(p);
			
			user =  new User(email, usuario.getPassword(), permisos);
					
		} else {
			throw new UsernameNotFoundException("El usuario no se encontro");
		}
		
		return user;
	}

}
