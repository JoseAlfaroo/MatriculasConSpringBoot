package com.wazaaa.joq.services;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wazaaa.joq.models.Usuario;
import com.wazaaa.joq.repositories.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UsuarioRepository usuarioRepository;
	
	public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
		
		if(usuario == null) {
			System.out.println("No se encontró usuario");
			throw new UsernameNotFoundException("Usuario no encontrado");
		}/*
		System.out.println("Rol del usuario "+ usuario.getNombres() + " " + usuario.getApellidos() + ": " + usuario.getRol());
		
	    String redirectUrl = "/logout";
	    
	    if ("USUARIO".equals(usuario.getRol())) {
	    	System.out.println(usuario.getNombres() + " " + usuario.getApellidos() + " inició sesión como " + usuario.getRol() );
	    	redirectUrl = "/usuario/";
	    	
	    } else if ("ADMINISTRADOR".equals(usuario.getRol())) {
	    	System.out.println(usuario.getNombres() + " " + usuario.getApellidos() + " inició sesión como " + usuario.getRol() );
	        redirectUrl = "/administrador/";
	    }
		
	    try {
	    	
	    	if (redirectUrl.equals("/logout")){
		    	System.out.println(usuario.getNombres() + " " + usuario.getApellidos() + " no pudo iniciar sesión, su rol es de " + usuario.getRol() );
	    	}*/
	    	
	        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

		List<String> roles = Arrays.asList(usuario.getRol());
		UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(usuario.getEmail())
                        .password(usuario.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
		
		return userDetails;
	}
	
	


}
