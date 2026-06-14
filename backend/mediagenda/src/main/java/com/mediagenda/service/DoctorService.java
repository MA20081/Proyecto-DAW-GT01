package com.mediagenda.service;

import com.mediagenda.dto.DoctorRequestDTO;
import com.mediagenda.dto.DoctorResponseDTO;
import com.mediagenda.model.Doctor;
import com.mediagenda.model.Especialidad;
import com.mediagenda.repository.DoctorRepository;
import com.mediagenda.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final EspecialidadRepository especialidadRepository;

    public DoctorService(DoctorRepository doctorRepository,
                         EspecialidadRepository especialidadRepository) {
        this.doctorRepository = doctorRepository;
        this.especialidadRepository = especialidadRepository;
    }

    private Doctor toEntity(DoctorRequestDTO dto) {
        Doctor d = new Doctor();
        d.setNombre(dto.getNombre());
        d.setTelefono(dto.getTelefono());
        if (dto.getEspecialidadIds() != null) {
            List<Especialidad> especialidades = new ArrayList<>();
            for (Long eid : dto.getEspecialidadIds()) {
                Especialidad e = especialidadRepository.findById(eid)
                        .orElseThrow(() -> new RuntimeException("Especialidad no encontrada: " + eid));
                especialidades.add(e);
            }
            d.setEspecialidades(especialidades);
        }
        return d;
    }

    private DoctorResponseDTO toDTO(Doctor d) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(d.getId());
        dto.setNombre(d.getNombre());
        dto.setTelefono(d.getTelefono());
        dto.setEspecialidades(
            d.getEspecialidades().stream()
             .map(Especialidad::getNombre)
             .collect(Collectors.toList())
        );
        return dto;
    }

    public DoctorResponseDTO crear(DoctorRequestDTO dto) {
        return toDTO(doctorRepository.save(toEntity(dto)));
    }

    public List<DoctorResponseDTO> listarTodos() {
        return doctorRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public DoctorResponseDTO obtenerPorId(Long id) {
        Doctor d = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con id: " + id));
        return toDTO(d);
    }

    public DoctorResponseDTO actualizar(Long id, DoctorRequestDTO dto) {
        Doctor d = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con id: " + id));
        d.setNombre(dto.getNombre());
        d.setTelefono(dto.getTelefono());
        if (dto.getEspecialidadIds() != null) {
            List<Especialidad> especialidades = new ArrayList<>();
            for (Long eid : dto.getEspecialidadIds()) {
                Especialidad e = especialidadRepository.findById(eid)
                        .orElseThrow(() -> new RuntimeException("Especialidad no encontrada: " + eid));
                especialidades.add(e);
            }
            d.setEspecialidades(especialidades);
        }
        return toDTO(doctorRepository.save(d));
    }

    public void eliminar(Long id) {
        doctorRepository.deleteById(id);
    }
}
