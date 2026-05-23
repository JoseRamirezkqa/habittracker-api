package com.habittracker.habittracker_api.servicio;

import com.habittracker.habittracker_api.modelo.Usuario;
import com.habittracker.habittracker_api.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario registrar(Usuario usuario) {
        if (usuarioRepositorio.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        return usuarioRepositorio.save(usuario);
    }

    public Optional<Usuario> login(String email, String password) {
        return usuarioRepositorio.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario actualizar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }
}