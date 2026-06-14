package com.mediagenda.service;

import com.mediagenda.dto.EspecialidadRequestDTO;
import com.mediagenda.dto.EspecialidadResponseDTO;
import com.mediagenda.model.Especialidad;
import com.mediagenda.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadService(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    private Especialidad toEntity(EspecialidadRequestDTO dto) {
        Especialidad e = new Especialidad();
        e.setNombre(dto.getNombre());
        return e;
    }

    private EspecialidadResponseDTO toDTO(Especialidad e) {
        EspecialidadResponseDTO dto = new EspecialidadResponseDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        return dto;
    }

    public EspecialidadResponseDTO crear(EspecialidadRequestDTO dto) {
        return toDTO(especialidadRepository.save(toEntity(dto)));
    }

    public List<EspecialidadResponseDTO> listarTodas() {
        return especialidadRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EspecialidadResponseDTO obtenerPorId(Long id) {
        Especialidad e = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con id: " + id));
        return toDTO(e);
    }

    public EspecialidadResponseDTO actualizar(Long id, EspecialidadRequestDTO dto) {
        Especialidad e = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con id: " + id));
        e.setNombre(dto.getNombre());
        return toDTO(especialidadRepository.save(e));
    }

    public void eliminar(Long id) {
        especialidadRepository.deleteById(id);
    }
}
