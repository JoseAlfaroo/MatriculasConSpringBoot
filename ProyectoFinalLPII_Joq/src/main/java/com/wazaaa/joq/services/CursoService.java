package com.wazaaa.joq.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wazaaa.joq.interfaces.InterfaceCurso;
import com.wazaaa.joq.interfaceservices.InterfaceCursoService;
import com.wazaaa.joq.models.Curso;

@Service
public class CursoService implements InterfaceCursoService{
	@Autowired
	private InterfaceCurso data;
	
	@Override
	public List<Curso> listarCurso() {
		// TODO Auto-generated method stub
		return (List<Curso>) data.findAll();
	}

	@Override
	public Optional<Curso> listarCursoPorId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	@Override
	public int guardarCurso(Curso c) {
		int res = 0;
		
		Curso curso = data.save(c);
		
		if(!curso.equals(null)) {
			res = 1;
		}
		
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public void eliminarCurso(int id) {
		data.deleteById(id);
		
	}
}
