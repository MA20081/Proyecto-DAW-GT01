package com.mediagenda.controller;

import com.mediagenda.dto.CitaRequestDTO;
import com.mediagenda.dto.CitaResponseDTO;
import com.mediagenda.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@Tag(name = "Citas Médicas", description = "Operaciones CRUD para la gestión de citas médicas")
@RequestMapping("/citas")
@Tag(name = "Citas", description = "API para gestión de citas médicas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    @Operation(
        summary = "Crear una nueva cita",
        description = "Registra una nueva cita médica en el sistema"
    )
    public ResponseEntity<CitaResponseDTO> crear(@RequestBody CitaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.crear(dto));
    }

    @GetMapping
    @Operation(
        summary = "Listar todas las citas",
        description = "Retorna la lista completa de todas las citas médicas registradas"
    )
    public ResponseEntity<List<CitaResponseDTO>> listar() {
        return ResponseEntity.ok(citaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener cita por ID",
        description = "Busca y retorna una cita médica específica por su ID"
    )
    public ResponseEntity<CitaResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar una cita",
        description = "Modifica los datos de una cita médica existente"
    )
    public ResponseEntity<CitaResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody CitaRequestDTO dto) {
        return ResponseEntity.ok(citaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar una cita",
        description = "Elimina permanentemente una cita médica del sistema por su ID"
    )
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
    @GetMapping
    @Operation(summary = "Obtener todas las citas")
    public ResponseEntity<List<CitaResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cita por ID")
    public ResponseEntity<CitaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cita")
    public ResponseEntity<CitaResponseDTO> crear(@RequestBody CitaRequestDTO request) {
        return new ResponseEntity<>(citaService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cita")
    public ResponseEntity<CitaResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody CitaRequestDTO request) {
        return ResponseEntity.ok(citaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cita")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
