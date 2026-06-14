package com.mediagenda.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "especialidades")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidades_seq")
    @SequenceGenerator(name = "especialidades_seq", sequenceName = "especialidades_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 80)
    private String nombre;

    @ManyToMany(mappedBy = "especialidades")
    private List<Doctor> doctores = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Doctor> getDoctores() { return doctores; }
    public void setDoctores(List<Doctor> doctores) { this.doctores = doctores; }
}
