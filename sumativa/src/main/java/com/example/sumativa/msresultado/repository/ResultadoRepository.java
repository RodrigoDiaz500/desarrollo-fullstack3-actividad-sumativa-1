package com.example.sumativa.msresultado.repository;

import com.example.sumativa.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Resultado.
 * Proporciona métodos CRUD básicos para interactuar con la tabla RESULTADO.
 */
@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    // JpaRepository hereda automáticamente los métodos necesarios para 
    // findAll(), findById(), save(), deleteById() que usa tu ResultadoService.

}