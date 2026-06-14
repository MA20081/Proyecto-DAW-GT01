package com.mediagenda.dto;

import java.util.List;

public class DoctorRequestDTO {

    private String nombre;
    private String telefono;
    private List<Long> especialidadIds;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<Long> getEspecialidadIds() { return especialidadIds; }
    public void setEspecialidadIds(List<Long> especialidadIds) { this.especialidadIds = especialidadIds; }
}
