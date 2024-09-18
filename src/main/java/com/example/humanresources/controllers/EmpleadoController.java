package com.example.humanresources.controllers;

import com.example.humanresources.Dto.EmpleadoDto;
import com.example.humanresources.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<EmpleadoDto> findAll() {
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> findById(@PathVariable Long id) {
        Optional<EmpleadoDto> empleadoDto = empleadoService.findById(id);
        return empleadoDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmpleadoDto save(@RequestBody EmpleadoDto empleadoDto) {
        return empleadoService.save(empleadoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> update(@PathVariable Long id, @RequestBody EmpleadoDto empleadoDto) {
        Optional<EmpleadoDto> updatedDto = empleadoService.update(id, empleadoDto);
        return updatedDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        empleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
