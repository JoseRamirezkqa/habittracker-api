package com.habittracker.habittracker_api.servicio;

import com.habittracker.habittracker_api.modelo.Habito;
import com.habittracker.habittracker_api.modelo.RegistroCumplimiento;
import com.habittracker.habittracker_api.repositorio.HabitoRepositorio;
import com.habittracker.habittracker_api.repositorio.RegistroCumplimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistroCumplimientoServicio {

    @Autowired
    private RegistroCumplimientoRepositorio registroRepositorio;

    @Autowired
    private HabitoRepositorio habitoRepositorio;

    public RegistroCumplimiento marcarCompletado(Long habitoId, String notas) {
        if (registroRepositorio.existsByHabitoIdAndFecha(habitoId, LocalDate.now())) {
            throw new RuntimeException("El hábito ya fue completado hoy");
        }

        Habito habito = habitoRepositorio.findById(habitoId)
                .orElseThrow(() -> new RuntimeException("Hábito no encontrado"));

        // Verificar si completó ayer — si no, reiniciar racha
        boolean completoAyer = registroRepositorio.existsByHabitoIdAndFecha(
                habitoId, LocalDate.now().minusDays(1));

        if (!completoAyer) {
            habito.setRachaActual(0);
        }

        RegistroCumplimiento registro = new RegistroCumplimiento();
        registro.setHabito(habito);
        registro.setFecha(LocalDate.now());
        registro.setCompletado(true);
        registro.setNotas(notas);

        // Incrementar racha
        habito.setRachaActual(habito.getRachaActual() + 1);
        if (habito.getRachaActual() > habito.getMejorRacha()) {
            habito.setMejorRacha(habito.getRachaActual());
        }

        habitoRepositorio.save(habito);
        return registroRepositorio.save(registro);
    }

    public List<RegistroCumplimiento> obtenerPorHabito(Long habitoId) {
        return registroRepositorio.findByHabitoId(habitoId);
    }

    public List<RegistroCumplimiento> obtenerPorSemana(Long habitoId, LocalDate inicio, LocalDate fin) {
        return registroRepositorio.findByHabitoIdAndFechaBetween(habitoId, inicio, fin);
    }

    public boolean estaCompletadoHoy(Long habitoId) {
        return registroRepositorio.existsByHabitoIdAndFecha(habitoId, LocalDate.now());
    }

    @Transactional
    public void desmarcarHoy(Long habitoId) {
        LocalDate hoy = LocalDate.now();
        if (!registroRepositorio.existsByHabitoIdAndFecha(habitoId, hoy)) {
            throw new RuntimeException("El hábito no estaba completado hoy");
        }

        Habito habito = habitoRepositorio.findById(habitoId)
                .orElseThrow(() -> new RuntimeException("Hábito no encontrado"));

        registroRepositorio.deleteByHabitoIdAndFecha(habitoId, hoy);

        if (habito.getRachaActual() > 0) {
            habito.setRachaActual(habito.getRachaActual() - 1);
        }

        habitoRepositorio.save(habito);
    }
}