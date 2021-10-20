package com.Libreria.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Libreria.Enums.Rol;
import com.Libreria.Repositorios.RepUsuario;
import com.Libreria.entidades.Usuario;

@Service
public class ServicioUsuario implements UserDetailsService{
	
	@Autowired
	private RepUsuario repUsuario;
	
	public void registroUsuario(String nombre, String apellido, String telefono, String email, String password) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setTelefono(telefono);
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setAlta(true);
		usuario.setRol(Rol.USUARIO);
		
		repUsuario.save(usuario);
		
	}
	
	public void registroAdmin(String nombre, String apellido, String telefono, String email, String password) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setTelefono(telefono);
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setAlta(true);
		usuario.setRol(Rol.ADMIN);
		
		repUsuario.save(usuario);
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Usuario usuario = repUsuario.findByEmail(email);
		 UserBuilder builder = null;
		 
		 if (usuario != null) {
			builder = User.withUsername(email);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString()));
		} else {
			
			throw new UsernameNotFoundException("El usuario no se encontro");
		}
		 
		return builder.build();
	}
	
	
	
}
