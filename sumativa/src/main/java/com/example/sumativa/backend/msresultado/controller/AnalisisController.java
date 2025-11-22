package com.example.sumativa.backend.msresultado.controller;


import com.example.sumativa.backend.model.Analisis;
import com.example.sumativa.backend.msresultado.service.AnalisisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analisis")
public class AnalisisController {
    
    @Autowired
    private AnalisisService analisisService;

    @GetMapping
    public List<Analisis> getAll() {
        return analisisService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analisis> getById(@PathVariable Long id) {
        return analisisService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Analisis> create(@RequestBody Analisis analisis) {
        Analisis savedAnalisis = analisisService.save(analisis);
        return new ResponseEntity<>(savedAnalisis, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analisis> update(@PathVariable Long id, @RequestBody Analisis analisisDetails) {
        try {
            Analisis updatedAnalisis = analisisService.update(id, analisisDetails);
            return ResponseEntity.ok(updatedAnalisis);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        analisisService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}