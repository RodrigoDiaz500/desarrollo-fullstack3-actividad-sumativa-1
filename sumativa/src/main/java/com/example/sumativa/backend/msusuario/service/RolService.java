package com.example.sumativa.backend.msusuario.service;

import com.example.sumativa.backend.model.Rol;
import com.example.sumativa.backend.msusuario.repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Optional<Rol> findById(Long id) {
        return rolRepository.findById(id);
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol update(Long id, Rol rolDetails) {
        return rolRepository.findById(id)
            .map(rol -> {
                rol.setNombreRol(rolDetails.getNombreRol());
                // Aquí podrías añadir más campos si tu modelo Rol los tiene
                return rolRepository.save(rol);
            })
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }

    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }
}