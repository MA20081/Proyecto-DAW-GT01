package com.mediagenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediagenda.model.cita;
import com.mediagenda.repository.CitaRepository;
import com.mediagenda.dto.CitaRequestDTO;
import com.mediagenda.dto.CitaResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    @Autowired
    private CitaRepository repository;

    // 🔹 GET (listar)
    public List<CitaResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 🔹 POST (guardar)
    public CitaResponseDTO guardar(CitaRequestDTO request) {
        cita entidad = toEntity(request);
        cita guardado = repository.save(entidad);
        return toResponseDTO(guardado);
    }

    // 🔹 PUT (actualizar)
    public CitaResponseDTO actualizar(Long id, CitaRequestDTO request) {
        cita existente = repository.findById(id).orElse(null);

        if (existente != null) {
            existente.setPaciente(request.getPaciente());
            existente.setFecha(request.getFecha());
            existente.setMotivo(request.getMotivo());

            return toResponseDTO(repository.save(existente));
        }
        return null;
    }

    // 🔹 DELETE
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // 🔄 ENTITY → RESPONSE DTO
    private CitaResponseDTO toResponseDTO(cita c) {
        CitaResponseDTO dto = new CitaResponseDTO();
        dto.setId(c.getId());
        dto.setPaciente(c.getPaciente());
        dto.setFecha(c.getFecha());
        dto.setMotivo(c.getMotivo());
        return dto;
    }

    // 🔄 REQUEST DTO → ENTITY
    private cita toEntity(CitaRequestDTO dto) {
        cita c = new cita();
        c.setPaciente(dto.getPaciente());
        c.setFecha(dto.getFecha());
        c.setMotivo(dto.getMotivo());
        return c;
    }
}
