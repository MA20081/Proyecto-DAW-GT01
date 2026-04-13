package com.mediagenda.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_paciente", nullable = false)
    private String nombrePaciente;

    @Column(name = "nombre_doctor", nullable = false)
    private String nombreDoctor;

    @Column(nullable = false)
    private String especialidad;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column
    private String estado;

    // =====================
    // Getters y Setters
    // =====================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String v) { this.nombrePaciente = v; }

    public String getNombreDoctor() { return nombreDoctor; }
    public void setNombreDoctor(String v) { this.nombreDoctor = v; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String v) { this.especialidad = v; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime v) { this.fechaHora = v; }

    public String getEstado() { return estado; }
    public void setEstado(String v) { this.estado = v; }
}
