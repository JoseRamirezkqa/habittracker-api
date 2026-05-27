package com.habittracker.habittracker_api.controlador;

import com.habittracker.habittracker_api.modelo.Habito;
import com.habittracker.habittracker_api.servicio.HabitoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habitos")
public class HabitoControlador {

    @Autowired
    private HabitoServicio habitoServicio;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Habito habito) {
        return ResponseEntity.ok(habitoServicio.crear(habito));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Habito>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(habitoServicio.obtenerPorUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/categoria/{categoria}")
    public ResponseEntity<List<Habito>> obtenerPorCategoria(
            @PathVariable Long usuarioId,
            @PathVariable String categoria) {
        return ResponseEntity.ok(habitoServicio.obtenerPorUsuarioYCategoria(usuarioId, categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return habitoServicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Habito habito) {
        habito.setId(id);
        return ResponseEntity.ok(habitoServicio.actualizar(habito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        habitoServicio.eliminar(id);
        return ResponseEntity.ok().build();
    }
}