package com.habittracker.habittracker_api.repositorio;

import com.habittracker.habittracker_api.modelo.Logro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LogroRepositorio extends JpaRepository<Logro, Long> {
    List<Logro> findByUsuarioId(Long usuarioId);
}