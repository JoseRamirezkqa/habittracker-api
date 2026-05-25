package com.habittracker.habittracker_api.controlador;

import com.habittracker.habittracker_api.modelo.Usuario;
import com.habittracker.habittracker_api.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(usuarioServicio.registrar(usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        return usuarioServicio.login(usuario.getEmail(), usuario.getPassword())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return usuarioServicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return ResponseEntity.ok(usuarioServicio.actualizar(usuario));
    }
    @PostMapping("/google")
public ResponseEntity<?> loginConGoogle(@RequestBody java.util.Map<String, String> datos) {
    try {
        String email = datos.get("email");
        String nombre = datos.get("nombre");
        
        // Buscar si el usuario ya existe
        return usuarioServicio.buscarPorEmail(email)
            .map(ResponseEntity::ok)
            .orElseGet(() -> {
                // Crear usuario nuevo con Google
                Usuario nuevo = new Usuario();
                nuevo.setEmail(email);
                nuevo.setNombre(nombre);
                nuevo.setPassword("GOOGLE_AUTH_" + email);
                return ResponseEntity.ok(usuarioServicio.registrar(nuevo));
            });
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error al autenticar con Google");
    }
}
}