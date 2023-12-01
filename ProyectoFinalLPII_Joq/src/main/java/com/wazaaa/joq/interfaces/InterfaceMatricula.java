package com.wazaaa.joq.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wazaaa.joq.models.Matricula;

@Repository
public interface InterfaceMatricula extends CrudRepository<Matricula, Integer> {
	
}
