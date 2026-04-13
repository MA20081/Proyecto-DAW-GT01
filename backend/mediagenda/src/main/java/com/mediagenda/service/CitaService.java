package com.mediagenda.service;

import com.mediagenda.dto.CitaRequestDTO;
import com.mediagenda.dto.CitaResponseDTO;
import com.mediagenda.model.Cita;
import com.mediagenda.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    // 🔄 DTO → Entidad
    private Cita toEntity(CitaRequestDTO dto) {
        Cita cita = new Cita();
        cita.setNombrePaciente(dto.getNombrePaciente());
        cita.setNombreDoctor(dto.getNombreDoctor());
        cita.setEspecialidad(dto.getEspecialidad());
        cita.setFechaHora(dto.getFechaHora());
        cita.setEstado(dto.getEstado() != null ? dto.getEstado() : "PENDIENTE");
        return cita;
    }

    // 🔄 Entidad → DTO
    private CitaResponseDTO toDTO(Cita cita) {
        CitaResponseDTO dto = new CitaResponseDTO();
        dto.setId(cita.getId());
        dto.setNombrePaciente(cita.getNombrePaciente());
        dto.setNombreDoctor(cita.getNombreDoctor());
        dto.setEspecialidad(cita.getEspecialidad());
        dto.setFechaHora(cita.getFechaHora());
        dto.setEstado(cita.getEstado());
        return dto;
    }

    // 🔹 POST - Crear
    public CitaResponseDTO crear(CitaRequestDTO dto) {
        return toDTO(citaRepository.save(toEntity(dto)));
    }

    // 🔹 GET - Listar todas
    public List<CitaResponseDTO> listarTodas() {
        return citaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 🔹 GET - Obtener por ID
    public CitaResponseDTO obtenerPorId(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
        return toDTO(cita);
    }

    // 🔹 PUT - Actualizar
    public CitaResponseDTO actualizar(Long id, CitaRequestDTO dto) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
        cita.setNombrePaciente(dto.getNombrePaciente());
        cita.setNombreDoctor(dto.getNombreDoctor());
        cita.setEspecialidad(dto.getEspecialidad());
        cita.setFechaHora(dto.getFechaHora());
        cita.setEstado(dto.getEstado());
        return toDTO(citaRepository.save(cita));
    }

    // 🔹 DELETE - Eliminar
    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}
