package com.example.humanresources.controllers;

import com.example.humanresources.Dto.PermisoDto;
import com.example.humanresources.services.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {
    @Autowired
    private PermisoService permisoService;

    @GetMapping
    public ResponseEntity<List<PermisoDto>> getAllPermisos() {
        List<PermisoDto> permisos = permisoService.findAll();
        return ResponseEntity.ok(permisos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisoDto> getPermisoById(@PathVariable Long id) {
        Optional<PermisoDto> permiso = permisoService.findById(id);
        return permiso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PermisoDto> createPermiso(@RequestBody PermisoDto permisoDto) {
        PermisoDto createdPermiso = permisoService.save(permisoDto);
        return ResponseEntity.ok(createdPermiso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermisoDto> updatePermiso(@PathVariable Long id, @RequestBody PermisoDto permisoDto) {
        Optional<PermisoDto> updatedPermiso = permisoService.update(id, permisoDto);
        return updatedPermiso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermiso(@PathVariable Long id) {
        permisoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
