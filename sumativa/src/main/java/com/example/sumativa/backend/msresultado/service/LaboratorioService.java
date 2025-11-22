package com.example.sumativa.backend.msresultado.service;

import com.example.sumativa.backend.model.Laboratorio;
import com.example.sumativa.backend.msresultado.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {
    
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public List<Laboratorio> findAll() {
        return laboratorioRepository.findAll();
    }

    public Optional<Laboratorio> findById(Long id) {
        return laboratorioRepository.findById(id);
    }

    public Laboratorio save(Laboratorio laboratorio) {
        laboratorio.setIdLaboratorio(null); 
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio update(Long id, Laboratorio laboratorioDetails) {
        return laboratorioRepository.findById(id)
            .map(laboratorio -> {
                laboratorio.setNombre(laboratorioDetails.getNombre());
                laboratorio.setDireccion(laboratorioDetails.getDireccion());
                return laboratorioRepository.save(laboratorio);
            })
            .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado con ID: " + id));
    }

    public void deleteById(Long id) {
        laboratorioRepository.deleteById(id);
    }
}