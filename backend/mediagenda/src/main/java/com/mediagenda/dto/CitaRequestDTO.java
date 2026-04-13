package com.mediagenda.dto;

import java.time.LocalDateTime;

public class CitaRequestDTO {

    private String nombrePaciente;
    private String nombreDoctor;
    private String especialidad;
    private LocalDateTime fechaHora;
    private String estado;

    // =====================
    // Getters y Setters
    // =====================
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
