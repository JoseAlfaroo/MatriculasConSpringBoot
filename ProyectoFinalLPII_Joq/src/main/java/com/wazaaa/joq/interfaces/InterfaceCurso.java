package com.wazaaa.joq.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wazaaa.joq.models.Curso;

@Repository
public interface InterfaceCurso extends CrudRepository<Curso, Integer> {
	
}
