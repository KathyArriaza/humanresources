package com.example.humanresources.controllers;

import com.example.humanresources.Dto.EvaluacionDto;
import com.example.humanresources.services.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionesController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public List<EvaluacionDto> getAllEvaluaciones() {
        return evaluacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDto> getEvaluacionById(@PathVariable Long id) {
        Optional<EvaluacionDto> evaluacionDto = evaluacionService.findById(id);
        return evaluacionDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EvaluacionDto> createEvaluacion(@RequestBody EvaluacionDto evaluacionDto) {
        EvaluacionDto createdEvaluacion = evaluacionService.save(evaluacionDto);
        return ResponseEntity.ok(createdEvaluacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionDto> updateEvaluacion(@PathVariable Long id, @RequestBody EvaluacionDto evaluacionDto) {
        Optional<EvaluacionDto> updatedEvaluacion = evaluacionService.update(id, evaluacionDto);
        return updatedEvaluacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluacion(@PathVariable Long id) {
        evaluacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
