package com.example.sumativa.msresultado.service;

import com.example.sumativa.model.Resultado;
import com.example.sumativa.msresultado.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    // GET: Obtener todos
    public List<Resultado> findAll() {
        return resultadoRepository.findAll();
    }

    // GET: Obtener por ID
    public Optional<Resultado> findById(Long id) {
        return resultadoRepository.findById(id);
    }

    // POST: Crear nuevo resultado
    public Resultado save(Resultado resultado) {
        // Lógica: Se podría validar que el Laboratorio, Analisis y Técnico existan.
        return resultadoRepository.save(resultado);
    }

    // PUT: Actualizar resultado
    public Resultado update(Long id, Resultado resultadoDetails) {
        return resultadoRepository.findById(id)
            .map(resultado -> {
                resultado.setResultadoDetalle(resultadoDetails.getResultadoDetalle());
                resultado.setLaboratorio(resultadoDetails.getLaboratorio());
                resultado.setAnalisis(resultadoDetails.getAnalisis());
                // ... actualizar otros campos según sea necesario
                return resultadoRepository.save(resultado);
            })
            .orElseThrow(() -> new RuntimeException("Resultado no encontrado con ID: " + id));
    }

    // DELETE: Eliminar
    public void deleteById(Long id) {
        resultadoRepository.deleteById(id);
    }
}