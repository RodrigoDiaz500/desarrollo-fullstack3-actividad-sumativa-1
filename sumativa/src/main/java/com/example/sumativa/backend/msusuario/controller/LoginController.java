package com.example.sumativa.backend.msusuario.controller;

import com.example.sumativa.backend.msusuario.dto.LoginRequest;
import com.example.sumativa.backend.msusuario.dto.LoginResponse;
import com.example.sumativa.backend.msusuario.repository.UsuarioRepository;
import com.example.sumativa.backend.msusuario.security.JwtService;
import com.example.sumativa.backend.model.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElse(null);

    if (usuario == null) {
        return ResponseEntity.status(404).body("❌ Usuario no encontrado");
    }

    // Validar contraseña
    if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
        return ResponseEntity.status(401).body("❌ Contraseña incorrecta");
    }

    String role = "ROLE_" + usuario.getRol().getNombreRol().toUpperCase();

    String token = jwtService.generateToken(
            new org.springframework.security.core.userdetails.User(
                    usuario.getEmail(),
                    usuario.getPasswordHash(),
                    List.of(new SimpleGrantedAuthority(role))
            )
    );

    return ResponseEntity.ok(
            new LoginResponse(
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getRol().getNombreRol(),
                    token
            )
    );
}

}
