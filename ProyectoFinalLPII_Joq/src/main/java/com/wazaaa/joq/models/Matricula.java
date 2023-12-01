package com.wazaaa.joq.models;

import java.time.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matricula")
public class Matricula {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

	@ManyToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "idcurso")
	private Curso curso;

	@Column(name = "fecha")
    private LocalDate fecha;
	
	@Column(name = "horainicio")
    private LocalTime horainicio;


	@Column(name = "estado")
    private String estado;
	

}
