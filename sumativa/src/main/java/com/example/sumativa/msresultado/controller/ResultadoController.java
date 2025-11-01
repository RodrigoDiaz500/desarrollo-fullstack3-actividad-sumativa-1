package com.example.sumativa.msresultado.controller;

import com.example.sumativa.model.Resultado;
import com.example.sumativa.msresultado.service.ResultadoService;
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

    // GET - Obtener todos los resultados
    @GetMapping
    public List<Resultado> getAll() {
        return resultadoService.findAll();
    }

    // GET - Obtener un resultado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Resultado> getById(@PathVariable Long id) {
        return resultadoService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Crear un nuevo resultado
    @PostMapping
    public ResponseEntity<Resultado> create(@RequestBody Resultado resultado) {
        Resultado savedResultado = resultadoService.save(resultado);
        return new ResponseEntity<>(savedResultado, HttpStatus.CREATED);
    }

    // PUT - Actualizar un resultado existente
    @PutMapping("/{id}")
    public ResponseEntity<Resultado> update(@PathVariable Long id, @RequestBody Resultado resultadoDetails) {
        try {
            Resultado updatedResultado = resultadoService.update(id, resultadoDetails);
            return ResponseEntity.ok(updatedResultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar un resultado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resultadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}