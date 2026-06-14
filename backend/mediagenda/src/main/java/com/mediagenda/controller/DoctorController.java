package com.mediagenda.controller;

import com.mediagenda.dto.DoctorRequestDTO;
import com.mediagenda.dto.DoctorResponseDTO;
import com.mediagenda.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@Tag(name = "Doctores", description = "Gestión de doctores y sus especialidades (relación N:M)")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @Operation(summary = "Registrar doctor", description = "Crea un doctor y le asigna especialidades existentes")
    public ResponseEntity<DoctorResponseDTO> crear(@RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.crear(dto));
    }

    @GetMapping
    @Operation(summary = "Listar doctores")
    public ResponseEntity<List<DoctorResponseDTO>> listar() {
        return ResponseEntity.ok(doctorService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener doctor por ID")
    public ResponseEntity<DoctorResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar doctor")
    public ResponseEntity<DoctorResponseDTO> actualizar(@PathVariable Long id,
                                                         @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.ok(doctorService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar doctor")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        doctorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
