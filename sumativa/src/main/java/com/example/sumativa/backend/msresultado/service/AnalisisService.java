package com.example.sumativa.backend.msresultado.service;

import com.example.sumativa.backend.model.Analisis;
import com.example.sumativa.backend.msresultado.repository.AnalisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalisisService {
    
    @Autowired
    private AnalisisRepository analisisRepository;

    public List<Analisis> findAll() {
        return analisisRepository.findAll();
    }

    public Optional<Analisis> findById(Long id) {
        return analisisRepository.findById(id);
    }

    public Analisis save(Analisis analisis) {
        analisis.setIdAnalisis(null); 
        return analisisRepository.save(analisis);
    }

    public Analisis update(Long id, Analisis analisisDetails) {
        return analisisRepository.findById(id)
            .map(analisis -> {
                analisis.setNombreAnalisis(analisisDetails.getNombreAnalisis());
                analisis.setDescripcion(analisisDetails.getDescripcion());
                return analisisRepository.save(analisis);
            })
            .orElseThrow(() -> new RuntimeException("Análisis no encontrado con ID: " + id));
    }

    public void deleteById(Long id) {
        analisisRepository.deleteById(id);
    }
}