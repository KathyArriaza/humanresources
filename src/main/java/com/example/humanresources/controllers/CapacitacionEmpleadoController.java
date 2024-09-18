package com.example.humanresources.controllers;

import com.example.humanresources.Dto.CapacitacionEmpleadoDto;
import com.example.humanresources.services.CapacitacionEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/capacitacion-empleado")
public class CapacitacionEmpleadoController {

    @Autowired
    private CapacitacionEmpleadoService capacitacionEmpleadoService;

    @GetMapping
    public List<CapacitacionEmpleadoDto> findAll() {
        return capacitacionEmpleadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CapacitacionEmpleadoDto> findById(@PathVariable Long id) {
        Optional<CapacitacionEmpleadoDto> capacitacionEmpleadoDto = capacitacionEmpleadoService.findById(id);
        return capacitacionEmpleadoDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CapacitacionEmpleadoDto save(@RequestBody CapacitacionEmpleadoDto capacitacionEmpleadoDto) {
        return capacitacionEmpleadoService.save(capacitacionEmpleadoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CapacitacionEmpleadoDto> update(@PathVariable Long id, @RequestBody CapacitacionEmpleadoDto capacitacionEmpleadoDto) {
        Optional<CapacitacionEmpleadoDto> updatedDto = capacitacionEmpleadoService.update(id, capacitacionEmpleadoDto);
        return updatedDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        capacitacionEmpleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
