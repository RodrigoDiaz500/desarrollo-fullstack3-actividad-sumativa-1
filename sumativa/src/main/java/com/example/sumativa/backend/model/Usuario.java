package com.example.sumativa.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID_USUARIO")
private Long idUsuario;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    // CORRECCIÓN: Relación ManyToOne con la entidad Rol (reemplaza Long idRol)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ROL", nullable = false)
    private Rol rol; 

    // Constructor vacío requerido por JPA
    public Usuario() {
    }
}