package com.example.sumativa.backend.msresultado.controller;

import com.example.sumativa.backend.model.Laboratorio;
import com.example.sumativa.backend.msresultado.service.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {
    
    @Autowired
    private LaboratorioService laboratorioService;

    @GetMapping
    public List<Laboratorio> getAll() {
        return laboratorioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getById(@PathVariable Long id) {
        return laboratorioService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Laboratorio> create(@RequestBody Laboratorio laboratorio) {
        Laboratorio savedLaboratorio = laboratorioService.save(laboratorio);
        return new ResponseEntity<>(savedLaboratorio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> update(@PathVariable Long id, @RequestBody Laboratorio laboratorioDetails) {
        try {
            Laboratorio updatedLaboratorio = laboratorioService.update(id, laboratorioDetails);
            return ResponseEntity.ok(updatedLaboratorio);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laboratorioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}