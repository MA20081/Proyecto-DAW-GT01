package com.mediagenda.controller;

import com.mediagenda.dto.PacienteRequestDTO;
import com.mediagenda.dto.PacienteResponseDTO;
import com.mediagenda.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "Gestión de pacientes registrados en el sistema")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @Operation(summary = "Registrar paciente", description = "Crea un nuevo paciente en el sistema")
    public ResponseEntity<PacienteResponseDTO> crear(@RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crear(dto));
    }

    @GetMapping
    @Operation(summary = "Listar pacientes", description = "Retorna todos los pacientes registrados")
    public ResponseEntity<List<PacienteResponseDTO>> listar() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener paciente por ID")
    public ResponseEntity<PacienteResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar paciente")
    public ResponseEntity<PacienteResponseDTO> actualizar(@PathVariable Long id,
                                                           @RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.ok(pacienteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar paciente")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
