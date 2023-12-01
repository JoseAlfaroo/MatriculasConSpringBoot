package com.wazaaa.joq.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wazaaa.joq.interfaces.InterfaceDocente;
import com.wazaaa.joq.interfaceservices.InterfaceDocenteService;
import com.wazaaa.joq.models.Docente;

@Service
public class DocenteService implements InterfaceDocenteService {

	@Autowired
	private InterfaceDocente data;
	
	@Override
	public List<Docente> listarDocente() {
		// TODO Auto-generated method stub
		return (List<Docente>) data.findAll();
	}

	@Override
	public Optional<Docente> listarDocentePorId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	@Override
	public int guardarDocente(Docente d) {
		int res = 0;
		
		Docente docente = data.save(d);
		
		if(!docente.equals(null)) {
			res = 1;
		}
		
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public void eliminarDocente(int id) {
		data.deleteById(id);
		
	}

}
