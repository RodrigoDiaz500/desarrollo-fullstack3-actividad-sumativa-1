package com.example.sumativa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ROL")
@Data // Genera getters, setters, toString, equals y hashCode
public class Rol {

    @Id
    @Column(name = "ID_ROL")
    private Long idRol;

    @Column(name = "NOMBRE_ROL", nullable = false)
    private String nombreRol;

    // Constructor vacío requerido por JPA
    public Rol() {
    }
}
