package com.wazaaa.joq.interfaceservices;

import java.util.*;

import com.wazaaa.joq.models.Docente;

public interface InterfaceDocenteService {
	public List<Docente> listarDocente();
	public Optional<Docente> listarDocentePorId(int id);
	public int guardarDocente(Docente d);
	public void eliminarDocente(int id);
}
