package com.mediagenda.controller;

import com.mediagenda.dto.EspecialidadRequestDTO;
import com.mediagenda.dto.EspecialidadResponseDTO;
import com.mediagenda.service.EspecialidadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@Tag(name = "Especialidades", description = "Catálogo de especialidades médicas (relación N:M con Doctores)")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @PostMapping
    @Operation(summary = "Crear especialidad")
    public ResponseEntity<EspecialidadResponseDTO> crear(@RequestBody EspecialidadRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadService.crear(dto));
    }

    @GetMapping
    @Operation(summary = "Listar especialidades")
    public ResponseEntity<List<EspecialidadResponseDTO>> listar() {
        return ResponseEntity.ok(especialidadService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener especialidad por ID")
    public ResponseEntity<EspecialidadResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar especialidad")
    public ResponseEntity<EspecialidadResponseDTO> actualizar(@PathVariable Long id,
                                                               @RequestBody EspecialidadRequestDTO dto) {
        return ResponseEntity.ok(especialidadService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar especialidad")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        especialidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
