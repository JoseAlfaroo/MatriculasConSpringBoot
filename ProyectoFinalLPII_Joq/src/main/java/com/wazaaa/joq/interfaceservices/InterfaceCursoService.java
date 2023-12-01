package com.wazaaa.joq.interfaceservices;

import java.util.List;
import java.util.Optional;

import com.wazaaa.joq.models.Curso;

public interface InterfaceCursoService {
	public List<Curso> listarCurso();
	public Optional<Curso> listarCursoPorId(int id);
	public int guardarCurso(Curso d);
	public void eliminarCurso(int id);
}
