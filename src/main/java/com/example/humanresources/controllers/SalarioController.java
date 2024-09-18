package com.example.humanresources.controllers;

import com.example.humanresources.Dto.SalarioDto;
import com.example.humanresources.services.SalarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salarios")
public class SalarioController {

    @Autowired
    private SalarioService salarioService;

    @GetMapping
    public List<SalarioDto> findAll() {
        return salarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalarioDto> findById(@PathVariable Long id) {
        Optional<SalarioDto> salarioDto = salarioService.findById(id);
        return salarioDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SalarioDto save(@RequestBody SalarioDto salarioDto) {
        return salarioService.save(salarioDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalarioDto> update(@PathVariable Long id, @RequestBody SalarioDto salarioDto) {
        Optional<SalarioDto> updatedDto = salarioService.update(id, salarioDto);
        return updatedDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        salarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
