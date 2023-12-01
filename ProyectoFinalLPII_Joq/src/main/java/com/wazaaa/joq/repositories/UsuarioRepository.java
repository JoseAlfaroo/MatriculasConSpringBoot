package com.wazaaa.joq.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wazaaa.joq.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("FROM Usuario WHERE email = :email")
    Usuario findUsuarioByEmail(@Param("email") String email);
	
}
