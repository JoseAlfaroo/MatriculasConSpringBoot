package com.wazaaa.joq.models;

import java.time.*;
import java.time.format.DateTimeFormatter;

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
@Table(name = "retiro")
public class Retiro {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "idmatricula")
    private Matricula matricula;

    @Column(name = "fecha")
    private LocalDate fecha;
    
    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    
}