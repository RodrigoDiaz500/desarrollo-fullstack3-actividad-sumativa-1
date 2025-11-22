package com.example.sumativa.backend.msusuario.controller;

import com.example.sumativa.backend.model.Rol;
import com.example.sumativa.backend.msusuario.service.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    // POST - Crear un nuevo rol
    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody Rol rol) {
        Rol savedRol = rolService.save(rol);
        return new ResponseEntity<>(savedRol, HttpStatus.CREATED);
    }

    // GET - Obtener todos los roles
    @GetMapping
    public List<Rol> getAll() {
        return rolService.findAll();
    }

    // GET - Obtener un rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getById(@PathVariable Long id) {
        return rolService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT - Actualizar un rol existente
    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable Long id, @RequestBody Rol rolDetails) {
        try {
            Rol updatedRol = rolService.update(id, rolDetails);
            return ResponseEntity.ok(updatedRol);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Eliminar un rol por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}