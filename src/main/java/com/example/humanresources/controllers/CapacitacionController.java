package com.example.humanresources.controllers;

import com.example.humanresources.Dto.CapacitacionDto;
import com.example.humanresources.services.CapacitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/capacitacion")
public class CapacitacionController {
    @Autowired
    private CapacitacionService capacitacionService;

    @GetMapping
    public List<CapacitacionDto> findAll() {
        return capacitacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CapacitacionDto> findById(@PathVariable Long id) {
        Optional<CapacitacionDto> capacitacionDto = capacitacionService.findById(id);
        return capacitacionDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CapacitacionDto save(@RequestBody CapacitacionDto capacitacionDto) {
        return capacitacionService.save(capacitacionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CapacitacionDto> update(@PathVariable Long id, @RequestBody CapacitacionDto capacitacionDto) {
        Optional<CapacitacionDto> updatedDto = capacitacionService.update(id, capacitacionDto);
        return updatedDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        capacitacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
