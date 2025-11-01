package com.example.sumativa.msresultado.controller;

import com.example.sumativa.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioController extends JpaRepository<Resultado, Long> {
    // Puedes añadir métodos de consulta específicos, ej: findByLaboratorioId()
}