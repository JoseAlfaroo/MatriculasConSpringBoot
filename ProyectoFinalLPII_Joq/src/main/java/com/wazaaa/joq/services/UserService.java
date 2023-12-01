package com.wazaaa.joq.services;

import org.springframework.stereotype.Service;

import com.wazaaa.joq.models.Usuario;
import com.wazaaa.joq.repositories.UsuarioRepository;

@Service
public class UserService {
	
	private final UsuarioRepository usuarioRepository;
	
	public UserService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario getUserByEmail(String email) {
		Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
		return usuario;
	}
	
	public Usuario createUser(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		usuarioRepository.flush();
		return nuevoUsuario;
	}


}
