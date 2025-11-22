package com.example.sumativa.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ROL")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL")
    private Long idRol;

    @Column(name = "NOMBRE_ROL", nullable = false, unique = true)
    private String nombreRol;

    public Rol() {
    }
}