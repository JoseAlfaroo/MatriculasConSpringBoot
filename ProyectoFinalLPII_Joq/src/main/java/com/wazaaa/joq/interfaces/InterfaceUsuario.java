package com.wazaaa.joq.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.wazaaa.joq.models.Usuario;

public interface InterfaceUsuario extends CrudRepository<Usuario, Integer> {

}
