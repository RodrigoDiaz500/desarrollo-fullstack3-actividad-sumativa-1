package com.example.sumativa.backend.msusuario.service;

import com.example.sumativa.backend.model.Usuario;
import com.example.sumativa.backend.msusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    // GET: Obtener todos los usuarios
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // GET: Obtener usuario por ID
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    // POST: Crear usuario (AHORA CON ENCRIPTACIÓN)
    public Usuario save(Usuario usuario) {
        // Encriptar la contraseña antes de guardar el hash (CRÍTICO)
        if (usuario.getPasswordHash() != null) {
            String passwordHash = passwordEncoder.encode(usuario.getPasswordHash());
            usuario.setPasswordHash(passwordHash);
        }
        usuario.setIdUsuario(null);
        return usuarioRepository.save(usuario);
    }

    // PUT: Actualizar usuario (CON ENCRIPTACIÓN Y RELACIÓN A ROL)
    public Usuario update(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNombre(usuarioDetails.getNombre());
                usuario.setEmail(usuarioDetails.getEmail());
                
                // Si se proporciona una nueva contraseña, la encriptamos (CRÍTICO)
                if (usuarioDetails.getPasswordHash() != null && !usuarioDetails.getPasswordHash().isEmpty()) {
                    String passwordHash = passwordEncoder.encode(usuarioDetails.getPasswordHash());
                    usuario.setPasswordHash(passwordHash);
                }
                
                // CORRECCIÓN: Asignar el objeto Rol completo, no un ID
                usuario.setRol(usuarioDetails.getRol()); 
                
                return usuarioRepository.save(usuario);
            })
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // DELETE: Eliminar usuario
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    
}