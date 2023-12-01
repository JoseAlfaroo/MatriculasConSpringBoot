package com.wazaaa.joq.models;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curso")
public class Curso {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

	// RECORDATORIO: Si la columna en la base de datos tiene por nombre "idDocente", JPA buscará la columna como "id_docente" 
	// No es necesario volver a crear la base de datos, basta con cambiar todo a minúsculas, así -> "iddocente"
	/*
	@Column(name = "iddocente")
    private String iddocente;*/

	@Column(name = "nombres")
    private String nombres;

	@Column(name = "horas")
    private BigDecimal horas;
	
	@ManyToOne
	@JoinColumn(name = "iddocente")
	private Docente docente;

}
