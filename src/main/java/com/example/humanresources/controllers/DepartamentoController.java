package com.example.humanresources.controllers;

import com.example.humanresources.Dto.DepartamentoDto;
import com.example.humanresources.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<DepartamentoDto> findAll() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDto> findById(@PathVariable Long id) {
        Optional<DepartamentoDto> departamentoDto = departamentoService.findById(id);
        return departamentoDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DepartamentoDto save(@RequestBody DepartamentoDto departamentoDto) {
        return departamentoService.save(departamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDto> update(@PathVariable Long id, @RequestBody DepartamentoDto departamentoDto) {
        Optional<DepartamentoDto> updatedDto = departamentoService.update(id, departamentoDto);
        return updatedDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        departamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
