package com.example.sumativa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ANALISIS")
@Data
public class Analisis {

    @Id
    @Column(name = "ID_ANALISIS")
    private Long idAnalisis;

    @Column(name = "NOMBRE_ANALISIS", nullable = false, unique = true)
    private String nombreAnalisis;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Analisis() {
    }
}