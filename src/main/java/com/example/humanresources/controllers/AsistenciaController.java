package com.example.humanresources.controllers;

import com.example.humanresources.Dto.AsistenciaDto;
import com.example.humanresources.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public List<AsistenciaDto> findAll() {
        return asistenciaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDto> findById(@PathVariable Long id) {
        Optional<AsistenciaDto> asistenciaDto = asistenciaService.findById(id);
        return asistenciaDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AsistenciaDto save(@RequestBody AsistenciaDto asistenciaDto) {
        return asistenciaService.save(asistenciaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDto> update(@PathVariable Long id, @RequestBody AsistenciaDto asistenciaDto) {
        Optional<AsistenciaDto> updatedDto = asistenciaService.update(id, asistenciaDto);
        return updatedDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        asistenciaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
