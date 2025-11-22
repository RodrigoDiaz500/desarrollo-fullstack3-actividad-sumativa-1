package com.example.sumativa.backend.msusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sumativa.backend.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Puedes añadir métodos aquí si necesitas buscar por nombre de rol
    // Ejemplo: Optional<Rol> findByNombreRol(String nombreRol);
}