package com.example.sumativa.msusuario.service;

import com.example.sumativa.model.Usuario;
import com.example.sumativa.msusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET: Obtener todos
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // GET: Obtener por ID
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    // POST: Crear nuevo
    public Usuario save(Usuario usuario) {
        // Lógica de negocio: Aquí se debería cifrar la contraseña (ej. con BCrypt)
        // Por simplicidad, solo guardamos.
        return usuarioRepository.save(usuario);
    }

    // PUT: Actualizar
    public Usuario update(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNombre(usuarioDetails.getNombre());
                usuario.setEmail(usuarioDetails.getEmail());
                // Asumiendo que el ROL se maneja en el objeto Usuario
                usuario.setRol(usuarioDetails.getRol());
                // No se actualiza la contraseña directamente en un PUT simple
                return usuarioRepository.save(usuario);
            })
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // DELETE: Eliminar
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}