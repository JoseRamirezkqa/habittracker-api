package com.habittracker.habittracker_api.controlador;

import com.habittracker.habittracker_api.modelo.RegistroCumplimiento;
import com.habittracker.habittracker_api.servicio.RegistroCumplimientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*")
public class RegistroCumplimientoControlador {

    @Autowired
    private RegistroCumplimientoServicio registroServicio;

    @PostMapping("/completar/{habitoId}")
    public ResponseEntity<?> marcarCompletado(
            @PathVariable Long habitoId,
            @RequestBody(required = false) Map<String, String> body) {
        try {
            String notas = body != null ? body.get("notas") : null;
            return ResponseEntity.ok(registroServicio.marcarCompletado(habitoId, notas));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/habito/{habitoId}")
    public ResponseEntity<List<RegistroCumplimiento>> obtenerPorHabito(@PathVariable Long habitoId) {
        return ResponseEntity.ok(registroServicio.obtenerPorHabito(habitoId));
    }

    @GetMapping("/habito/{habitoId}/semana")
    public ResponseEntity<List<RegistroCumplimiento>> obtenerPorSemana(@PathVariable Long habitoId) {
        LocalDate inicio = LocalDate.now().minusDays(7);
        LocalDate fin = LocalDate.now();
        return ResponseEntity.ok(registroServicio.obtenerPorSemana(habitoId, inicio, fin));
    }

    @GetMapping("/habito/{habitoId}/hoy")
    public ResponseEntity<Boolean> estaCompletadoHoy(@PathVariable Long habitoId) {
        return ResponseEntity.ok(registroServicio.estaCompletadoHoy(habitoId));
    }
    @DeleteMapping("/desmarcar/{habitoId}")
    public ResponseEntity<?> desmarcarHoy(@PathVariable Long habitoId) {
        try {
            registroServicio.desmarcarHoy(habitoId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}