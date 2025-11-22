package com.example.sumativa.backend.msresultado.controller;

import com.example.sumativa.backend.model.Resultado;
import com.example.sumativa.backend.msresultado.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;

    @GetMapping
    public List<Resultado> getAll() {
        return resultadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultado> getById(@PathVariable Long id) {
        return resultadoService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resultado> create(@RequestBody Resultado resultado) {
        try {
            Resultado savedResultado = resultadoService.save(resultado);
            return new ResponseEntity<>(savedResultado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Error de datos (ej: falta Analysis o Laboratorio)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
        } catch (RuntimeException e) {
            // Error de FK (ej: Análisis o Laboratorio no existe)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultado> update(@PathVariable Long id, @RequestBody Resultado resultadoDetails) {
        try {
            Resultado updatedResultado = resultadoService.update(id, resultadoDetails);
            return ResponseEntity.ok(updatedResultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resultadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}