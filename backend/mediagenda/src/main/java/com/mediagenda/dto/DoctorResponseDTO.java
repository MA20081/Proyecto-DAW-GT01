package com.mediagenda.dto;

import java.util.List;

public class DoctorResponseDTO {

    private Long id;
    private String nombre;
    private String telefono;
    private List<String> especialidades;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<String> getEspecialidades() { return especialidades; }
    public void setEspecialidades(List<String> especialidades) { this.especialidades = especialidades; }
}
