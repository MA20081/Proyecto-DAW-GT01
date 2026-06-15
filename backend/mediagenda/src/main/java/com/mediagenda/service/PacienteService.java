package com.mediagenda.service;

import com.mediagenda.dto.PacienteRequestDTO;
import com.mediagenda.dto.PacienteResponseDTO;
import com.mediagenda.model.Paciente;
import com.mediagenda.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    private Paciente toEntity(PacienteRequestDTO dto) {
        Paciente p = new Paciente();
        p.setNombre(dto.getNombre());
        p.setTelefono(dto.getTelefono());
        p.setCorreo(dto.getCorreo());
        return p;
    }

    private PacienteResponseDTO toDTO(Paciente p) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setTelefono(p.getTelefono());
        dto.setCorreo(p.getCorreo());
        return dto;
    }

    public PacienteResponseDTO crear(PacienteRequestDTO dto) {
        return toDTO(pacienteRepository.save(toEntity(dto)));
    }

    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PacienteResponseDTO obtenerPorId(Long id) {
        Paciente p = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
        return toDTO(p);
    }

    public PacienteResponseDTO actualizar(Long id, PacienteRequestDTO dto) {
        Paciente p = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
        p.setNombre(dto.getNombre());
        p.setTelefono(dto.getTelefono());
        p.setCorreo(dto.getCorreo());
        return toDTO(pacienteRepository.save(p));
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
