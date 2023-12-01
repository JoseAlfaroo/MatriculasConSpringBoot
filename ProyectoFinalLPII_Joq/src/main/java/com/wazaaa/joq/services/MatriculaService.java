package com.wazaaa.joq.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wazaaa.joq.interfaces.InterfaceMatricula;
import com.wazaaa.joq.interfaceservices.InterfaceMatriculaService;
import com.wazaaa.joq.models.Matricula;

@Service
public class MatriculaService implements InterfaceMatriculaService {
	@Autowired
	private InterfaceMatricula data;
	
	@Override
	public List<Matricula> listarMatricula() {
		// TODO Auto-generated method stub
		return (List<Matricula>) data.findAll();
	}

	@Override
	public Optional<Matricula> listarMatriculaPorId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	@Override
	public int guardarMatricula(Matricula m) {
		int res = 0;
		
		Matricula matricula = data.save(m);
		
		if(!matricula.equals(null)) {
			res = 1;
		}
		
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public void eliminarMatricula(int id) {
		data.deleteById(id);
		
	}
}
