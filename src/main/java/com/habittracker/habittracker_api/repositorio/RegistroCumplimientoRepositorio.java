package com.habittracker.habittracker_api.repositorio;

import com.habittracker.habittracker_api.modelo.RegistroCumplimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegistroCumplimientoRepositorio extends JpaRepository<RegistroCumplimiento, Long> {
    List<RegistroCumplimiento> findByHabitoId(Long habitoId);
    List<RegistroCumplimiento> findByHabitoIdAndFechaBetween(Long habitoId, LocalDate inicio, LocalDate fin);
    Optional<RegistroCumplimiento> findByHabitoIdAndFecha(Long habitoId, LocalDate fecha);
    boolean existsByHabitoIdAndFecha(Long habitoId, LocalDate fecha);
    void deleteByHabitoIdAndFecha(Long habitoId, java.time.LocalDate fecha);
    
}
