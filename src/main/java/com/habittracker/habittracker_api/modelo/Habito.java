package com.habittracker.habittracker_api.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "habitos")
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String icono;

    private String categoria;

    private String frecuencia; // DIARIO, SEMANAL

    private String diasSemana; // "L,M,X,J,V" separado por comas

    private String horaRecordatorio;

    private int rachaActual = 0;

    private int mejorRacha = 0;

    private LocalDate fechaCreacion = LocalDate.now();

    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public String getDiasSemana() { return diasSemana; }
    public void setDiasSemana(String diasSemana) { this.diasSemana = diasSemana; }

    public String getHoraRecordatorio() { return horaRecordatorio; }
    public void setHoraRecordatorio(String horaRecordatorio) { this.horaRecordatorio = horaRecordatorio; }

    public int getRachaActual() { return rachaActual; }
    public void setRachaActual(int rachaActual) { this.rachaActual = rachaActual; }

    public int getMejorRacha() { return mejorRacha; }
    public void setMejorRacha(int mejorRacha) { this.mejorRacha = mejorRacha; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}