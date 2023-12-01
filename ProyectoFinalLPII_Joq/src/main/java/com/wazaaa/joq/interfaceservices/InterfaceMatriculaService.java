package com.wazaaa.joq.interfaceservices;

import java.util.List;
import java.util.Optional;

import com.wazaaa.joq.models.Matricula;

public interface InterfaceMatriculaService {
	public List<Matricula> listarMatricula();
	Optional<Matricula> listarMatriculaPorId(int id);
	public int guardarMatricula(Matricula d);
	public void eliminarMatricula(int id);
}
