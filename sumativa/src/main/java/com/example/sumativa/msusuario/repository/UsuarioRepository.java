package com.example.sumativa.msusuario.repository;

import com.example.sumativa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar un usuario por su email para la lógica de inicio de sesión
    Optional<Usuario> findByEmail(String email);
}