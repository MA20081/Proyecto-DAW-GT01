package com.mediagenda.controller;

import com.mediagenda.dto.CitaRequestDTO;
import com.mediagenda.dto.CitaResponseDTO;
import com.mediagenda.service.CitaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
@Tag(name = "Citas", description = "API para gestión de citas médicas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
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