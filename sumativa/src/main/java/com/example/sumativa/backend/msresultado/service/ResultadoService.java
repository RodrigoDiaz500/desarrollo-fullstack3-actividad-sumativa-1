package com.example.sumativa.backend.msresultado.service;

import com.example.sumativa.backend.model.Resultado;
import com.example.sumativa.backend.msresultado.repository.ResultadoRepository;
import com.example.sumativa.backend.msresultado.repository.AnalisisRepository;
import com.example.sumativa.backend.msresultado.repository.LaboratorioRepository;
import com.example.sumativa.backend.msusuario.repository.UsuarioRepository; // Importa el Repository del otro módulo

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoService {

    @Autowired private ResultadoRepository resultadoRepository;
    @Autowired private AnalisisRepository analisisRepository;
    @Autowired private LaboratorioRepository laboratorioRepository;
    @Autowired private UsuarioRepository usuarioRepository; 

    // Método de validación para asegurar que las entidades relacionadas existen
    private void validateRelatedEntities(Resultado resultado) {
        if (resultado.getAnalisis() == null || resultado.getAnalisis().getIdAnalisis() == null) {
            throw new IllegalArgumentException("El Análisis es requerido.");
        }
        if (analisisRepository.findById(resultado.getAnalisis().getIdAnalisis()).isEmpty()) {
            throw new RuntimeException("Análisis con ID " + resultado.getAnalisis().getIdAnalisis() + " no encontrado.");
        }
        
        if (resultado.getLaboratorio() == null || resultado.getLaboratorio().getIdLaboratorio() == null) {
            throw new IllegalArgumentException("El Laboratorio es requerido.");
        }
        if (laboratorioRepository.findById(resultado.getLaboratorio().getIdLaboratorio()).isEmpty()) {
            throw new RuntimeException("Laboratorio con ID " + resultado.getLaboratorio().getIdLaboratorio() + " no encontrado.");
        }

        // El usuarioTecnico no es obligatorio (JoinColumn sin nullable=false), pero si se proporciona, debe existir.
        if (resultado.getUsuarioTecnico() != null && resultado.getUsuarioTecnico().getIdUsuario() != null) {
            if (usuarioRepository.findById(resultado.getUsuarioTecnico().getIdUsuario()).isEmpty()) {
                throw new RuntimeException("Usuario Técnico con ID " + resultado.getUsuarioTecnico().getIdUsuario() + " no encontrado.");
            }
        }
    }
    
    public List<Resultado> findAll() {
        return resultadoRepository.findAll();
    }

    public Optional<Resultado> findById(Long id) {
        return resultadoRepository.findById(id);
    }
    
    public Resultado save(Resultado resultado) {
        validateRelatedEntities(resultado);
        resultado.setIdResultado(null);
        return resultadoRepository.save(resultado);
    }

    public Resultado update(Long id, Resultado resultadoDetails) {
        return resultadoRepository.findById(id)
            .map(resultadoExistente -> {
                // Validamos las nuevas entidades relacionadas antes de asignar
                validateRelatedEntities(resultadoDetails); 

                resultadoExistente.setFechaAnalisis(resultadoDetails.getFechaAnalisis());
                resultadoExistente.setResultadoDetalle(resultadoDetails.getResultadoDetalle());
                
                // Actualizar relaciones (Hibernate usará los IDs de los objetos)
                resultadoExistente.setAnalisis(resultadoDetails.getAnalisis());
                resultadoExistente.setLaboratorio(resultadoDetails.getLaboratorio());
                resultadoExistente.setUsuarioTecnico(resultadoDetails.getUsuarioTecnico());
                
                return resultadoRepository.save(resultadoExistente);
            })
            .orElseThrow(() -> new RuntimeException("Resultado no encontrado con ID: " + id));
    }

    public void deleteById(Long id) {
        resultadoRepository.deleteById(id);
    }
}