package com.habittracker.habittracker_api.servicio;

import com.habittracker.habittracker_api.modelo.Habito;
import com.habittracker.habittracker_api.repositorio.HabitoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HabitoServicio {

    @Autowired
    private HabitoRepositorio habitoRepositorio;

    public Habito crear(Habito habito) {
        return habitoRepositorio.save(habito);
    }

    public List<Habito> obtenerPorUsuario(Long usuarioId) {
        return habitoRepositorio.findByUsuarioIdAndActivoTrue(usuarioId);
    }

    public List<Habito> obtenerPorUsuarioYCategoria(Long usuarioId, String categoria) {
        return habitoRepositorio.findByUsuarioIdAndCategoriaAndActivoTrue(usuarioId, categoria);
    }

    public Optional<Habito> buscarPorId(Long id) {
        return habitoRepositorio.findById(id);
    }

    public Habito actualizar(Habito habito) {
        return habitoRepositorio.save(habito);
    }

    public void eliminar(Long id) {
        habitoRepositorio.findById(id).ifPresent(h -> {
            h.setActivo(false);
            habitoRepositorio.save(h);
        });
    }
}