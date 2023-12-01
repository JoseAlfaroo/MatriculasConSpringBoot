package com.wazaaa.joq.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "docente")
public class Docente {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

	@Column(name = "nombres")
    private String nombres;

	@Column(name = "apellidos")
    private String apellidos;

	@Column(name = "telefono")
    private String telefono;

	@Column(name = "correo")
    private String correo;
	
}
