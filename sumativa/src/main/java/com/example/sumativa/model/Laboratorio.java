package com.example.sumativa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "LABORATORIO")
@Data
public class Laboratorio {

    @Id
    @Column(name = "ID_LABORATORIO")
    private Long idLaboratorio;

    @Column(name = "NOMBRE", nullable = false, unique = true)
    private String nombre;

    @Column(name = "DIRECCION")
    private String direccion;

    public Laboratorio() {
    }
}