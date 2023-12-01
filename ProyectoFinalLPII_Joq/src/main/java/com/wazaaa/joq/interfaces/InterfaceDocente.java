package com.wazaaa.joq.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wazaaa.joq.models.Docente;

@Repository
public interface InterfaceDocente extends CrudRepository<Docente, Integer> {

}
