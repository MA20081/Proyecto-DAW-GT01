package com.mediagenda.service;

import com.mediagenda.dto.CitaRequestDTO;
import com.mediagenda.dto.CitaResponseDTO;
import com.mediagenda.model.Cita;
import com.mediagenda.model.Doctor;
import com.mediagenda.model.Paciente;
import com.mediagenda.repository.CitaRepository;
import com.mediagenda.repository.DoctorRepository;
import com.mediagenda.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final DoctorRepository doctorRepository;

    public CitaService(CitaRepository citaRepository,
                       PacienteRepository pacienteRepository,
                       DoctorRepository doctorRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
    }

    private Cita toEntity(CitaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + dto.getPacienteId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con id: " + dto.getDoctorId()));

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setEspecialidad(dto.getEspecialidad());
        cita.setFechaHora(dto.getFechaHora());
        cita.setEstado(dto.getEstado() != null ? dto.getEstado() : "PENDIENTE");
        return cita;
    }

    private CitaResponseDTO toDTO(Cita cita) {
        CitaResponseDTO dto = new CitaResponseDTO();
        dto.setId(cita.getId());
        dto.setPacienteId(cita.getPaciente().getId());
        dto.setNombrePaciente(cita.getPaciente().getNombre());
        dto.setDoctorId(cita.getDoctor().getId());
        dto.setNombreDoctor(cita.getDoctor().getNombre());
        dto.setEspecialidad(cita.getEspecialidad());
        dto.setFechaHora(cita.getFechaHora());
        dto.setEstado(cita.getEstado());
        return dto;
    }

    // POST
    public CitaResponseDTO crear(CitaRequestDTO dto) {
        return toDTO(citaRepository.save(toEntity(dto)));
    }

    // GET all
    public List<CitaResponseDTO> listarTodas() {
        return citaRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // GET by ID
    public CitaResponseDTO obtenerPorId(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
        return toDTO(cita);
    }

    // PUT
    public CitaResponseDTO actualizar(Long id, CitaRequestDTO dto) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + dto.getPacienteId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con id: " + dto.getDoctorId()));

        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setEspecialidad(dto.getEspecialidad());
        cita.setFechaHora(dto.getFechaHora());
        cita.setEstado(dto.getEstado());
        return toDTO(citaRepository.save(cita));
    }

    // DELETE
    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}
