package com.Libreria.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Libreria.Repositorios.RepUsuario;
import com.Libreria.entidades.Usuario;

@Service
public class ServicioUsuario implements UserDetailsService{
	
	@Autowired
	private RepUsuario repUsuario;
	
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
