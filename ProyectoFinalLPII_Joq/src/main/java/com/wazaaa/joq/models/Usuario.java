package com.wazaaa.joq.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

	@Column(name = "email")
    private String email;

	@Column(name = "password")
    private String password;

	@Column(name = "nombres")
    private String nombres;

	@Column(name = "apellidos")
    private String apellidos;

	@Column(name = "rol")
    private String rol;
    
    public Usuario(String email, String password, String nombres, String apellidos) {
        this.email = email;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
}
