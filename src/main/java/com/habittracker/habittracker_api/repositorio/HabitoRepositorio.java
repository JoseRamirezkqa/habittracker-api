package com.habittracker.habittracker_api.repositorio;

import com.habittracker.habittracker_api.modelo.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HabitoRepositorio extends JpaRepository<Habito, Long> {
    List<Habito> findByUsuarioIdAndActivoTrue(Long usuarioId);
    List<Habito> findByUsuarioIdAndCategoriaAndActivoTrue(Long usuarioId, String categoria);
}