package com.wazaaa.joq.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wazaaa.joq.models.Retiro;

@Repository
public interface InterfaceRetiro extends CrudRepository<Retiro, Integer> {
	
}
