package com.habittracker.habittracker_api.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registros_cumplimiento")
public class RegistroCumplimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "habito_id", nullable = false)
    private Habito habito;

    @Column(nullable = false)
    private LocalDate fecha = LocalDate.now();

    private boolean completado = true;

    private String notas;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Habito getHabito() { return habito; }
    public void setHabito(Habito habito) { this.habito = habito; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public boolean isCompletado() { return completado; }
    public void setCompletado(boolean completado) { this.completado = completado; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}