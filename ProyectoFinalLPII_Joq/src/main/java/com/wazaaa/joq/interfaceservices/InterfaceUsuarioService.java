package com.wazaaa.joq.interfaceservices;

import java.util.List;
import java.util.Optional;
import com.wazaaa.joq.models.Usuario;

public interface InterfaceUsuarioService {
	public List<Usuario> listarUsuario();
	public Optional<Usuario> listarUsuarioPorId(int id);
	public int guardarUsuario(Usuario d);
	public void eliminarUsuario(int id);
}
